package com.zen.beans;

public class AbstractEvent {
	
	private int eventId;
	private String eventName;
	private Double eventPrice;
	private Double durationHours;
	private int maxNubr;
	private EventType eventType;
	private int activityId;
	private int contributorId;
	private String eventDay;
	private String startTime;
	private String endTime;


	public AbstractEvent() {
		super();
	}
	
	public AbstractEvent(int eventId, String eventName, Double eventPrice, Double durationHours, int maxNubr, EventType eventType, int activityId, int contributorId) {
		super();
		this.eventId = eventId;
		this.eventName = eventName;
		this.eventPrice = eventPrice;
		this.durationHours = durationHours;
		this.maxNubr = maxNubr;
		this.eventType = eventType;
		this.activityId = activityId;
		this.contributorId = contributorId ; 
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public Double getEventPrice() {
		return eventPrice;
	}

	public void setEventPrice(Double eventPrice) {
		this.eventPrice = eventPrice;
	}

	public Double getDurationHours() {
		return durationHours;
	}

	public void setDurationHours(Double durationHours) {
		this.durationHours = durationHours;
	}

	public int getMaxNubr() {
		return maxNubr;
	}

	public void setMaxNubr(int maxNubr) {
		this.maxNubr = maxNubr;
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	public int getActivityId() {
		return activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	public int getContributor() {
		return contributorId;
	}

	public void setContributor(int contributorId) {
		this.contributorId = contributorId;
	}

	public String getEventDay() {
		return eventDay;
	}

	public void setEventDay(String eventDay) {
		this.eventDay = eventDay;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	
	
}
