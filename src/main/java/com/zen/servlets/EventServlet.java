package com.zen.servlets;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.zen.beans.AbstractEvent;
import com.zen.dao.AuthentificationException;
import com.zen.dao.DAOAuthen;
import com.zen.dao.DAOFactory;
import com.zen.dao.EventDao;


@Path("/Events")
@Produces({ "application/json" })
@Consumes({ "application/json" })
public class EventServlet {

	@Context
	private UriInfo context;
	private EventDao  eventDao;


	@GET
	public List<AbstractEvent> getAll() {
		this.eventDao = DAOFactory.getInstance().getEventDao();
		List<AbstractEvent> events = this.eventDao.findAll();
		return events;
	}

	/* OK
	@GET
	public void create(){
		AbstractEvent event = new Event("eventName", "eventPrice", "maxNbr", "durationHours");
		this.eventDao = DAOFactory.getInstance().getEventDao();
		this.eventDao.create(event);
	}
	 */	
	

	@GET
	@Path("{id}")
	public AbstractEvent getOneJSON(@PathParam("id") String id) {
		this.eventDao = DAOFactory.getInstance().getEventDao();
		AbstractEvent event = this.eventDao.findById(id);
		return event;
	}
	
	

	/* ok
	@GET
	@Path("{id}")
	public void delete(@PathParam("id") int id) {
		this.eventDao = DAOFactory.getInstance().getEventDao();
		this.eventDao.delete(id);
	}
	 */
	
	@GET
	@Path("/{idE}/subscribe")
	public Response subscribeEvent(@PathParam("idE") String idE, @HeaderParam("token") String token) {
		DAOAuthen authen = new DAOAuthen();
		String idU;
		try {
			idU = Integer.toString(authen.authenToken(token));
		} catch (AuthentificationException e) {
			// TODO Auto-generated catch block
			return Response.status(403).entity(e.getMessage()).build();
		}
		this.eventDao = DAOFactory.getInstance().getEventDao();
		this.eventDao.subscribeEvent(idE, idU);
		return Response.status(201).build();
	}
	
	@GET
	@Path("/{idE}/unsubscribe")
	public Response unsubscribeEvent(@PathParam("idE") String idE, @HeaderParam("token") String token) {
		DAOAuthen authen = new DAOAuthen();
		String idU;
		try {
			idU = Integer.toString(authen.authenToken(token));
		} catch (AuthentificationException e) {
			// TODO Auto-generated catch block
			return Response.status(403).entity(e.getMessage()).build();
		}
		this.eventDao = DAOFactory.getInstance().getEventDao();
		this.eventDao.unsubscribeEvent(idE, idU);
		return Response.status(201).build();
	}

}
