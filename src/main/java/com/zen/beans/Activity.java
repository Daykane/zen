package com.zen.beans;



public class Activity {
	
	private int activityId;
	private String activityName;
	private String activityShortDesc;
	private String activityLongDesc;
	
	
	public Activity() {
		super();
	}


	public Activity(int activityId, String activityName, String activityShortDesc, String activityLongDesc) {
		super();
		this.activityId = activityId;
		this.activityName = activityName;
		this.activityShortDesc = activityShortDesc;
		this.activityLongDesc = activityLongDesc;
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
	

}
