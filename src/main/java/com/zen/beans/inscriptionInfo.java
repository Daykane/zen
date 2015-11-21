package com.zen.beans;

public class inscriptionInfo {

	private int eventId;
	private int userId;
	
	
	
	public inscriptionInfo() {
		super();
	}



	public inscriptionInfo(int eventId, int userId) {
		super();
		this.eventId = eventId;
		this.userId = userId;
	}



	public int getEventId() {
		return eventId;
	}



	public void setEventId(int eventId) {
		this.eventId = eventId;
	}



	public int getUserId() {
		return userId;
	}



	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
	
}
