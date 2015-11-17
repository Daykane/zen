package com.zen.servlets;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

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
	public User connection(@FormParam("password") String password,
			@FormParam("mail") String mail){
		//String mail = "mail";
		//String password = "password";
		this.userDao = DAOFactory.getInstance().getUserDao();
		User user = this.userDao.connection(mail, password);
		return user;
	}


}