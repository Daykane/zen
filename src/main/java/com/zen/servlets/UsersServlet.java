package com.zen.servlets;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import static com.zen.dao.DAOUtilitaire.*;

import com.zen.beans.AbstractEvent;
import com.zen.beans.Password;
import com.zen.beans.User;
import com.zen.dao.AuthentificationException;
import com.zen.dao.DAOAuthen;
import com.zen.dao.DAOException;
import com.zen.dao.DAOExceptionMail;
import com.zen.dao.DAOFactory;
import com.zen.dao.EventDao;
import com.zen.dao.UserDao;


@Path("/Users")
@Produces({ "application/json" })
@Consumes({ "application/json" })
public class UsersServlet {
	@Context
	private UriInfo context;
	private UserDao  userDao;
	private EventDao eventDao;
	
	
	@GET
    public List<User> getAll() {
		this.userDao = DAOFactory.getInstance().getUserDao();
				List<User> users = this.userDao.findAll();
				return users;
    }
	
	@GET
	@Path("{id}")
	public Object getOneJSON(@PathParam("id") String id,@HeaderParam("token") String token) {
		DAOAuthen authen = new DAOAuthen();
		String idU;
		//idU ="7";
		
		try {
			idU = Integer.toString(authen.authenToken(token));
		} catch (AuthentificationException e) {
			// TODO Auto-generated catch block
			return Response.status(403).entity(e.getMessage()).build();
		}
		this.userDao = DAOFactory.getInstance().getUserDao();
		User user = this.userDao.findById(id);
		return user;

	}
	

	@POST
	public Response create(User user){
		//Verify user is not null
		if(user==null){
			return Response.status(400).entity("error in json format").build();
		}
		user.setPassword(Sha1(user.getPassword()));
		
		this.userDao = DAOFactory.getInstance().getUserDao();
		try {
			this.userDao.create(user);
		} catch (DAOExceptionMail e) {
			return Response.status(400).entity("{\"mailError\": \"true\"}").build();
		} catch (DAOException e) {
			return Response.status(400).build();
		}
		return Response.status(201).build();
	}
		
	
	@DELETE
	@Path("{id}")
	public void delete(@PathParam("id") int id) {
		this.userDao = DAOFactory.getInstance().getUserDao();
		this.userDao.delete(id);
	}
	
	@PUT
	@Path("{id}")
	public Response update(User user, @PathParam("id") int id){
		if(user == null){
			return Response.status(400).entity("error in json format").build();
		}
		user.setId(id);
		//user.setPassword(Sha1(user.getPassword()));
		this.userDao = DAOFactory.getInstance().getUserDao();
		try {
			this.userDao.update(user);
		} catch (DAOExceptionMail e) {
			return Response.status(400).entity("{\"mailError\": \"true\"}").build();
		}
		return Response.status(204).build();
	}
	
	@PUT
	@Path("{id}/changePassword")
	public Response changePassword(Password password,@PathParam("id") String id,@HeaderParam("token") String token){
		DAOAuthen authen = new DAOAuthen();
		String idU;
		//idU ="7";
		
		try {
			idU = Integer.toString(authen.authenToken(token));
		} catch (AuthentificationException e) {
			return Response.status(403).entity(e.getMessage()).build();
		}
		
		this.userDao = DAOFactory.getInstance().getUserDao();
		User user = this.userDao.findById(id);
		if(!Sha1(password.getOldPassword()).equals(user.getPassword())){
			return Response.status(403).entity("{\"passwordError\": \"true\"}").build();
		}
		String updatePassword = Sha1(password.getNewPassword());
		this.userDao.updatePassword(updatePassword,idU);
		return Response.status(204).build();
		
	}
	
	@GET
	@Path("{id}/Events")
	public List<AbstractEvent> getAllEvents(@PathParam("id") String id) {
		this.eventDao = DAOFactory.getInstance().getEventDao();
		List<AbstractEvent> events = this.eventDao.findAllEvent(id);
		return events;

	}
}
