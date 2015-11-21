package com.zen.beans;

import java.util.Date;

public class PunctualEvent extends AbstractEvent {

	private Date punctualEventDate;


	public PunctualEvent() {
		super();
		
	}


	public PunctualEvent(int eventId, String eventName, Double eventPrice, Double durationHours, int maxNubr,
			EventType eventType,Date punctualEventDate , int activityId, int contributorId ) {
		super(eventId, eventName, eventPrice, durationHours, maxNubr, eventType, activityId, contributorId);
		this.punctualEventDate = punctualEventDate;
	}


	public Date getPunctualEventDate() {
		return punctualEventDate;
	}


	public void setPunctualEventDate(Date punctualEventDate) {
		this.punctualEventDate = punctualEventDate;
	}


}
