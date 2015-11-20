package com.zen.servlets;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import com.zen.beans.User;
import com.zen.dao.DAOFactory;
import com.zen.dao.UserDao;


@Path("/login")
@Produces({ "application/json" })
@Consumes({ "application/json" })
public class LoginServlet {
	@Context
	private UriInfo context;
	private UserDao  userDao;
	
	@POST 
	//@GET
	public User connection(User user){
		String mail = user.getMail();
		String password = user.getPassword();
		//String mail = "";
		//String password = "";
		this.userDao = DAOFactory.getInstance().getUserDao();
		SecureRandom random = new SecureRandom();
		String token = new BigInteger(130, random).toString(32);
		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();		 
		Timestamp currentTimestamp = new Timestamp(now.getTime());
		User userC = this.userDao.connection(mail, password,token,currentTimestamp);
		return userC;
	}


}
