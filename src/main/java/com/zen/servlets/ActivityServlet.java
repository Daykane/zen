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

import com.zen.beans.AbstractEvent;
import com.zen.beans.Activity;
import com.zen.dao.DAOFactory;
import com.zen.dao.EventDao;
import com.zen.dao.ActivityDao;
import com.zen.dao.DAOException;


@Path("/Activities")
@Produces({ "application/json" })
@Consumes({ "application/json" })
public class ActivityServlet {

	@Context
	private UriInfo context;
	private ActivityDao  activityDao;
	private EventDao eventDao;


	@GET
	public List<Activity> getAll() {
		this.activityDao = DAOFactory.getInstance().getActivityDao();
		List<Activity> activities = this.activityDao.findAll();
		return activities;
	}


	@GET
	@Path("{id}")
	public Activity getOneJSON(@PathParam("id") String id) {
		this.activityDao = DAOFactory.getInstance().getActivityDao();
		Activity activity = this.activityDao.findById(id);
		return activity;

	}

	@GET
	@Path("{id}/Events")
	public List<AbstractEvent> getEventActivities(@PathParam("id") String id) {
		this.eventDao = DAOFactory.getInstance().getEventDao();
		List<AbstractEvent>  events = this.eventDao.findByActivity(id);

		return events;
	}

	@GET
	@Path("{id}/Events/{idE}")
	public AbstractEvent getEventActivitiesId(@PathParam("id") String id,@PathParam("idE") String idE) {
		this.eventDao = DAOFactory.getInstance().getEventDao();
		AbstractEvent  event = this.eventDao.findByActivityById(id,idE);

		return event;
	}
	
	@POST
	public Response create(Activity activity){
		//Verify activity is not null
		if(activity==null){
			return Response.status(400).entity("error in json format").build();
		}
		this.activityDao = DAOFactory.getInstance().getActivityDao();
		try {
			this.activityDao.create(activity);
		} 
		catch (DAOException e) {
			return Response.status(400).build();
		}
		return Response.status(201).build();
	}

	@PUT
	@Path("{idA}")
	public Response update(Activity activity, @PathParam("idA") int id){
		if(activity == null){
			return Response.status(400).entity("error in json format").build();
		}
		this.activityDao = DAOFactory.getInstance().getActivityDao();
		this.activityDao.updateActivity(activity,id);
		return Response.status(204).build();
	}

	@DELETE
	@Path("{id}")
	public void delete(@PathParam("id") int id) {
		this.activityDao = DAOFactory.getInstance().getActivityDao();
		this.activityDao.delete(id);
	}

}
