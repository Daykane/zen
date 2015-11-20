package com.zen.dao;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import com.zen.beans.User;

public class DAOAuthen {

	
	private UserDao userDao;

	public void authentification(String id, String req, String signature) throws AuthentificationException{
		this.userDao = DAOFactory.getInstance().getUserDao();	
		User user = this.userDao.findById(id);	
		checkValidityToken(user.getToken(),user.getTimetamps());
		String mySignature = generateSignature(req,user.getToken());
		compare(mySignature,signature);
	}

	
	private void checkValidityToken(String token, Timestamp timetamps) throws AuthentificationException {
		
		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();		 
		Timestamp currentTimestamp = new Timestamp(now.getTime());
		//Duration validity for token
		long duration = (5*60*100000);
		//long duration = (48*60*100000);
		
		timetamps.setTime(timetamps.getTime() + duration);;
		if(timetamps.before(currentTimestamp)){
			throw new AuthentificationException("Please reconnection");
		}		
	}

	private String generateSignature(String req, String token) {
		String reqForSign = req+":"+token;
		  byte[] output = null;
	        
		try {
			MessageDigest md = MessageDigest.getInstance("SHA1");
			output = md.digest();
	         try {
				md.update(reqForSign.getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} 
	      	 output = md.digest();
	      	
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return bytesToHex(output);
	}
	
	private void compare(String mySignature, String signature) throws AuthentificationException {
		System.out.println("Mysignature :" + mySignature);
		System.out.println("Signature Send :" + signature);
		if (mySignature != signature){
			throw new AuthentificationException("Different signature");
		}
	}
	
	public static String bytesToHex(byte[] b) {
	      char hexDigit[] = {'0', '1', '2', '3', '4', '5', '6', '7',
	                         '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
	      StringBuffer buf = new StringBuffer();
	      for (int j=0; j<b.length; j++) {
	         buf.append(hexDigit[(b[j] >> 4) & 0x0f]);
	         buf.append(hexDigit[b[j] & 0x0f]);
	      }
	      return buf.toString();
	   }
	

	
}
