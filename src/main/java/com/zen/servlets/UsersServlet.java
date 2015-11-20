package com.zen.servlets;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

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
	
	/*
	@POST
	public void create(@FormParam("password") String password,
			@FormParam("lastName") String lastName,
			@FormParam("firstName") String firstName,
			@FormParam("adr1") String adr1,
			@FormParam("adr2") String adr2,			
			@FormParam("pc") String pc,
			@FormParam("town") String town,
			@FormParam("phone") String phone,
			@FormParam("mail") String mail
			){
		System.out.println("passeword" +password);
		System.out.println("lastName" +lastName);
		System.out.println("firstName" +firstName);
		System.out.println("mail" +mail);
		User user = new User();
		user.setPassword(password);
		user.setLastName(lastName);
		user.setFirstName(firstName);
		user.setAdr1(adr1);
		user.setAdr2(adr2);
		user.setPc(pc);
		user.setTown(town);
		user.setPhone(phone);
		user.setMail(mail);
		this.userDao = DAOFactory.getInstance().getUserDao();
		this.userDao.create(user);
	}
	*/
	@POST
	public Response create(User user){
		//System.out.println("user : " + user.toString());
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
		
	/* ok
	@GET
	@Path("{id}")
	public void delete(@PathParam("id") int id) {
		this.userDao = DAOFactory.getInstance().getUserDao();
		this.userDao.delete(id);
	}
	*/
}
