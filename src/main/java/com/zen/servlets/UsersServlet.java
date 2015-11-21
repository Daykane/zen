package com.zen.servlets;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import static com.zen.dao.DAOUtilitaire.*;
import com.zen.beans.User;
import com.zen.dao.DAOException;
import com.zen.dao.DAOExceptionMail;
import com.zen.dao.DAOFactory;
import com.zen.dao.UserDao;


@Path("/Users")
@Produces({ "application/json" })
@Consumes({ "application/json" })
public class UsersServlet {
	@Context
	private UriInfo context;
	private UserDao  userDao;
	
	
	@GET
    public List<User> getAll() {
		this.userDao = DAOFactory.getInstance().getUserDao();
				List<User> users = this.userDao.findAll();
				return users;
    }
	
	@GET
	@Path("{id}")
	public User getOneJSON(@PathParam("id") String id) {
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
			return Response.status(500).build();
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
		user.setPassword(Sha1(user.getPassword()));
		this.userDao = DAOFactory.getInstance().getUserDao();
		this.userDao.update(user);
		return Response.status(204).build();
	}
}
