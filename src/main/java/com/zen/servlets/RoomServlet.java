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

import com.zen.beans.AbstractRoom;
import com.zen.beans.Accessory;
import com.zen.dao.AccessoryDao;
import com.zen.dao.DAOException;
import com.zen.dao.DAOFactory;
import com.zen.dao.RoomDao;



@Path("/Rooms")
@Produces({ "application/json" })
@Consumes({ "application/json" })
public class RoomServlet {

	@Context
	private UriInfo context;
	private RoomDao  roomDao;
	private AccessoryDao accessoryDao;


	@GET
	public List<AbstractRoom> getAll() {
		this.roomDao = DAOFactory.getInstance().getRoomDao();
		List<AbstractRoom> rooms = this.roomDao.findAll();
		return rooms;
	}
	
	@POST
	public Response create(AbstractRoom room){
		//Verify room is not null
		if(room==null){
			return Response.status(400).entity("error in json format").build();
		}
		this.roomDao = DAOFactory.getInstance().getRoomDao();
		try {
			this.roomDao.create(room);
		} 
		catch (DAOException e) {
			return Response.status(400).build();
		}
		return Response.status(201).build();
	}
	
	@PUT
	@Path("{idR}")
	public Response update(AbstractRoom room, @PathParam("idR") int id){
		if(room == null){
			return Response.status(400).entity("error in json format").build();
		}
		room.setRoomId(id);
		this.roomDao = DAOFactory.getInstance().getRoomDao();
		this.roomDao.update(room);
		return Response.status(204).build();
	}

	@DELETE
	@Path("{id}")
	public void delete(@PathParam("id") int id) {
		this.roomDao = DAOFactory.getInstance().getRoomDao();
		this.roomDao.delete(id);
	}


	@GET
	@Path("{id}")
	public AbstractRoom getOneJSON(@PathParam("id") String id) {
		this.roomDao = DAOFactory.getInstance().getRoomDao();
		AbstractRoom room = this.roomDao.findById(id);
		return room;

	}


	@GET
	@Path("{id}/Accessories")
	public List<Accessory> getAccessoryRooms(@PathParam("id") String id) {
		this.accessoryDao = DAOFactory.getInstance().getAccessoryDao();
		List<Accessory>  accessories = this.accessoryDao.findByRoom(id);

		return accessories;
	}

	@GET
	@Path("{id}/Accessories/{idA}")
	public Accessory getAccessoryRoomsId(@PathParam("id") String id,@PathParam("idA") String idA) {
		this.accessoryDao = DAOFactory.getInstance().getAccessoryDao();
		Accessory  accessory = this.accessoryDao.findByRoomById(id,idA);
		/* Test pour update quantite accessoire
				this.roomDao = DAOFactory.getInstance().getRoomDao();
				accessory.setQuantity(accessory.getQuantity()+1);
				this.roomDao.updateQuantityAccessoryInRoom(id, accessory);*/
		return accessory;
	}

}
