package com.zen.beans;

import java.util.ArrayList;

public class Manager extends AbstractRole {

	private ArrayList<AbstractEvent> listEvents;

	public Manager() {
		super();
	}

	public Manager(int roleId, ArrayList<AbstractEvent> listEvents) {
		super(roleId);
		this.listEvents = listEvents;
	}
	
	public ArrayList<AbstractEvent> getListEvents() {
		return listEvents;
	}

	public void setListEvents(ArrayList<AbstractEvent> listEvents) {
		this.listEvents = listEvents;
	}
	

	//Methods

	public void createEvent(AbstractEvent event){}

	public void updateEvent(AbstractEvent event){}




}
