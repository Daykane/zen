package com.zen.servlets;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import com.zen.beans.Accessory;
import com.zen.dao.DAOFactory;
import com.zen.dao.AccessoryDao;


@Path("/Accessories")
@Produces({ "application/json" })
@Consumes({ "application/json" })
public class AccessoryServlet {

	@Context
	private UriInfo context;
	private AccessoryDao  accessoryDao;


	@GET
	public List<Accessory> getAll() {
		this.accessoryDao = DAOFactory.getInstance().getAccessoryDao();
		List<Accessory> accessories = this.accessoryDao.findAll();
		return accessories;
	}

	/* OK
	@GET
	public void create(){
		Accessory accessory = new Accessory("accessoryName");
		this.accessoryDao = DAOFactory.getInstance().getAccessoryDao();
		this.accessoryDao.create(accessory);
	}
	 */	
	

	@GET
	@Path("{id}")
	public Accessory getOneJSON(@PathParam("id") String id) {
		this.accessoryDao = DAOFactory.getInstance().getAccessoryDao();
		Accessory accessory = this.accessoryDao.findById(id);
		return accessory;
	}
	
	

	/* ok
	@GET
	@Path("{id}")
	public void delete(@PathParam("id") int id) {
		this.accessoryDao = DAOFactory.getInstance().getAccessoryDao();
		this.accessoryDao.delete(id);
	}
	 */

}
