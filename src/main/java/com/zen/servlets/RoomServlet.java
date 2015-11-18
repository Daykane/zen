package com.zen.servlets;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import com.zen.beans.AbstractRoom;

import com.zen.dao.DAOFactory;
import com.zen.dao.RoomDao;



@Path("/Rooms")
@Produces({ "application/json" })
@Consumes({ "application/json" })
public class RoomServlet {

	@Context
	private UriInfo context;
	private RoomDao  roomDao;


	@GET
	public List<AbstractRoom> getAll() {
		this.roomDao = DAOFactory.getInstance().getRoomDao();
		List<AbstractRoom> rooms = this.roomDao.findAll();
		return rooms;
	}

	/* OK
	@GET
	public void create(){
		AbstractRoom room = new AbstractRoom("roomName", "superficy");
		this.roomDao = DAOFactory.getInstance().getRoomDao();
		this.roomDao.create(room);
	}
	 */	
	

	@GET
	@Path("{id}")
	public AbstractRoom getOneJSON(@PathParam("id") String id) {
		this.roomDao = DAOFactory.getInstance().getRoomDao();
		AbstractRoom room = this.roomDao.findById(id);
		return room;

	}
	
	
	/* ok
	@GET
	@Path("{id}")
	public void delete(@PathParam("id") int id) {
		this.roomDao = DAOFactory.getInstance().getRoomDao();
		this.roomDao.delete(id);
	}
	 */

}
