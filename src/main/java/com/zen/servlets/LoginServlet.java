package com.zen.servlets;

import javax.security.auth.login.LoginException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import static com.zen.dao.DAOUtilitaire.Sha1;
import com.zen.beans.LoginInfo;
import com.zen.beans.User;
import com.zen.dao.DAOException;
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
	public Object connection(LoginInfo user){
		String mail = user.getMail();
		String password = Sha1(user.getPassword());

		this.userDao = DAOFactory.getInstance().getUserDao();
		
		SecureRandom random = new SecureRandom();
		String token = new BigInteger(130, random).toString(32);
		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();		 
		Timestamp currentTimestamp = new Timestamp(now.getTime());
		
		User userC = null;		
		try {
			userC = this.userDao.connection(mail, password,token,currentTimestamp);
		} catch (DAOException e) {
			e.printStackTrace();
		} catch (LoginException e) {
			return Response.status(400).entity("{\"UserError\": \"true\"}").build();
		}
		return userC;
	}


}
