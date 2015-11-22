package com.zen.beans;

import java.util.ArrayList;
import java.util.Date;

public class RepetitiveEvent extends AbstractEvent{
	
	private ArrayList<Date> representations;

	
	public RepetitiveEvent() {
		super();
		
	}

	public RepetitiveEvent(int eventId, String eventName, Double eventPrice, Double durationHours, int maxNubr,
			EventType eventType, ArrayList<Date> representations, int activityId, int contributorId) {
		super(eventId, eventName, eventPrice, durationHours, maxNubr, eventType, activityId, contributorId);
		this.representations = representations;
	}

	public ArrayList<Date> getRepresentations() {
		return representations;
	}

	public void setRepresentations(ArrayList<Date> representations) {
		this.representations = representations;
	}
	
	
	

}
