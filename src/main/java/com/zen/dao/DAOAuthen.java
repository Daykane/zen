package com.zen.dao;

import java.sql.Timestamp;

import com.zen.beans.User;

public class DAOAuthen {

	
	private UserDao userDao;

	public void authentification(String id, String req, String signature){
		User user = this.userDao.findById(id);
		checkValidityToken(user.getToken(),user.getTimetamps());
		String mySignature = generateSignature(req,user.getToken());
		compare(mySignature,signature);
	}

	
	private void checkValidityToken(String token, Timestamp timetamps) {
		System.out.println();
		
	}

	private String generateSignature(String req, String token) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void compare(String mySignature, String signature) {
		// TODO Auto-generated method stub
		
	}
	

	
}
