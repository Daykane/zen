package com.zen.beans;

public class AbstractEvent {
	
	private int eventId;
	private String eventName;
	private Double eventPrice;
	private Double durationHours;
	private int maxNubr;
	private EventType eventType;
	private int activityId;
	


	public AbstractEvent() {
		super();
	}
	
	public AbstractEvent(int eventId, String eventName, Double eventPrice, Double durationHours, int maxNubr, EventType eventType, int activityId) {
		super();
		this.eventId = eventId;
		this.eventName = eventName;
		this.eventPrice = eventPrice;
		this.durationHours = durationHours;
		this.maxNubr = maxNubr;
		this.eventType = eventType;
		this.activityId = activityId;
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
	
	
	
}
