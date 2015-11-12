package com.zen.servlets;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import com.zen.beans.User;
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
    
	/* OK
	@GET
	public void create(){
		User user = new User("passwordServlt","lastNameServlt", "firstNameServlt", "adr1Servlt","adr2Servlt", "pcServlt","townServlt",
				"phoneServlt", "mailServlt");
		this.userDao = DAOFactory.getInstance().getUserDao();
		this.userDao.create(user);
	}
	*/	
	/*
	@GET ok
	public User connection(){
		String mail = "mail";
		String password = "password";
		this.userDao = DAOFactory.getInstance().getUserDao();
		User user = this.userDao.connection(mail, password);
		return user;
	}
	*/
	
	@GET
	@Path("{id}")
	public User getOneJSON(@PathParam("id") String id) {
		this.userDao = DAOFactory.getInstance().getUserDao();
		User user = this.userDao.findById(id);
		return user;

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
