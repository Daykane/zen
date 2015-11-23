package com.zen.beans;

import java.util.Map;

public class AbstractRoom {
	
	private int roomId;
	private String roomName;
	private int superficy;
	//private Map<Accessory,Integer> roomAccesory; // couple (Accessory, IdQuantity)
	
	
	public AbstractRoom() {
		super();
	}


	public AbstractRoom(int roomId, String roomName, int superficy/*, Map<Accessory, Integer> roomAccesory*/) {
		super();
		this.roomId = roomId;
		this.roomName = roomName;
		this.superficy = superficy;
		//this.roomAccesory = roomAccesory;
	}


	public int getRoomId() {
		return roomId;
	}


	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}


	public String getRoomName() {
		return roomName;
	}


	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}


	public int getSuperficy() {
		return superficy;
	}


	public void setSuperficy(int superficy) {
		this.superficy = superficy;
	}

	/*
	public Map<Accessory, Integer> getRoomAccesory() {
		return roomAccesory;
	}


	public void setRoomAccesory(Map<Accessory, Integer> roomAccesory) {
		this.roomAccesory = roomAccesory;
	}
	*/
	
	
	
	

}
