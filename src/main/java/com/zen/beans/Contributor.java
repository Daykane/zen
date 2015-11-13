package com.zen.beans;

import java.util.ArrayList;

public class Contributor extends AbstractRole {

	private String contShortDesc;
	private String contLongDesc;
	private ArrayList<AbstractEvent> listEvents;


	public Contributor() {
		super();
	}

	public Contributor(int roleId, String contShortDesc, String contLongDesc,ArrayList<AbstractEvent> listEvents) {
		super(roleId);
		this.contShortDesc = contShortDesc; 
		this.contLongDesc = contLongDesc;
		this.listEvents = listEvents;
	}

	public String getContShortDesc() {
		return contShortDesc;
	}

	public void setContShortDesc(String contShortDesc) {
		this.contShortDesc = contShortDesc;
	}

	public String getContLongDesc() {
		return contLongDesc;
	}

	public void setContLongDesc(String contLongDesc) {
		this.contLongDesc = contLongDesc;
	}

	public ArrayList<AbstractEvent> getListEvents() {
		return listEvents;
	}

	public void setListEvents(ArrayList<AbstractEvent> listEvents) {
		this.listEvents = listEvents;
	}
	
	


}
