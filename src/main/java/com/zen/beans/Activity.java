package com.zen.beans;

import java.util.ArrayList;

public class Activity {
	
	private int activityId;
	private String activityName;
	private String activityShortDesc;
	private String activityLongDesc;
	private ArrayList<AbstractEvent> listEvent;
	
	
	public Activity() {
		super();
	}


	public Activity(int activityId, String activityName, String activityShortDesc, String activityLongDesc, ArrayList<AbstractEvent> listEvent) {
		super();
		this.activityId = activityId;
		this.activityName = activityName;
		this.activityShortDesc = activityShortDesc;
		this.activityLongDesc = activityLongDesc;
		this.listEvent = listEvent;
	}


	public int getActivityId() {
		return activityId;
	}


	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}


	public String getActivityName() {
		return activityName;
	}


	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}


	public String getActivityShortDescr() {
		return activityShortDesc;
	}


	public void setActivityShortDescr(String activityShortDescr) {
		this.activityShortDesc = activityShortDescr;
	}


	public String getActivityLongDesc() {
		return activityLongDesc;
	}


	public void setActivityLongDescr(String activityLongDesc) {
		this.activityLongDesc = activityLongDesc;
	}


	public ArrayList<AbstractEvent> getListEvent() {
		return listEvent;
	}


	public void setListEvent(ArrayList<AbstractEvent> listEvent) {
		this.listEvent = listEvent;
	}
	
	

}
