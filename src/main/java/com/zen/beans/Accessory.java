package com.zen.beans;

public class Accessory {
	
	private int accessoryId;
	private String accessoryName;
	private int quantity;
	
	
	public Accessory() {
		super();
	}


	public Accessory(int accessoryId, String accessoryName, int quantity) {
		super();
		this.accessoryId = accessoryId;
		this.accessoryName = accessoryName;
		this.quantity =  quantity;
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


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	
	
}
