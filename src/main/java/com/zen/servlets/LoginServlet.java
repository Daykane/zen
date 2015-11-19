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
import java.util.Calendar;

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
	
	//@POST 
	@GET
	public User connection(){
		String mail = "mail";
		String password = "password";
		this.userDao = DAOFactory.getInstance().getUserDao();
		SecureRandom random = new SecureRandom();
		String token = new BigInteger(130, random).toString(32);
		//System.out.println("token :" + token);
		 Calendar calendar = Calendar.getInstance();
		// System.out.println("time in millis :" + calendar.getTimeInMillis());
		 calendar.add(Calendar.HOUR, 8);
		User user = this.userDao.connection(mail, password,token,calendar);
		return user;
	}


}
