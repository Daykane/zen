package com.zen.servlets;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import com.zen.beans.AbstractEvent;
import com.zen.beans.Activity;
import com.zen.dao.DAOFactory;
import com.zen.dao.EventDao;
import com.zen.dao.ActivityDao;


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

	/* OK
	@GET
	public void create(){
		Activity activity = new Activity("activityName", "activityShortDesc", "activityLongDesc");
		this.activityDao = DAOFactory.getInstance().getActivityDao();
		this.activityDao.create(activity);
	}
	 */	
	

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
	
	/* ok
	@GET
	@Path("{id}")
	public void delete(@PathParam("id") int id) {
		this.activityDao = DAOFactory.getInstance().getActivityDao();
		this.activityDao.delete(id);
	}
	 */

}
