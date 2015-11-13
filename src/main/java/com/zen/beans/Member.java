package com.zen.beans;

import java.util.ArrayList;
import java.util.Date;

public class Member extends AbstractRole {
	
	private Date payFeeDate;
	private ArrayList<AbstractEvent> listEvents;

	public Member() {
		super();
	}

	public Member(int roleId, Date payFeeDate, ArrayList<AbstractEvent> listEvents) {
		super(roleId);
		this.payFeeDate = payFeeDate;
		this.listEvents = listEvents;
	}

	public Date getPayFeeDate() {
		return payFeeDate;
	}

	public void setPayFeeDate(Date payFeeDate) {
		this.payFeeDate = payFeeDate;
	}
	
	public ArrayList<AbstractEvent> getListEvents() {
		return listEvents;
	}

	public void setListEvents(ArrayList<AbstractEvent> listEvents) {
		this.listEvents = listEvents;
	}

	//Methods
	
	
	public void registerEvt(AbstractEvent event){}
	
	
	
	

}
