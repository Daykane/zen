package com.zen.beans;

public class Accessory {
	
	private int accessoryId;
	private String accessoryName;
	
	
	public Accessory() {
		super();
	}


	public Accessory(int accessoryId, String accessoryName) {
		super();
		this.accessoryId = accessoryId;
		this.accessoryName = accessoryName;
	}


	public int getAccessoryId() {
		return accessoryId;
	}


	public void setAccessoryId(int accessoryId) {
		this.accessoryId = accessoryId;
	}


	public String getAccessoryName() {
		return accessoryName;
	}


	public void setAccessoryName(String accessoryName) {
		this.accessoryName = accessoryName;
	}
	
}
