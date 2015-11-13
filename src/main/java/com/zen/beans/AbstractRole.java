package com.zen.beans;

public abstract class AbstractRole {
	
	
	private int roleId;

	public AbstractRole() {
		// TODO Auto-generated constructor stub
	}
	
	
	public AbstractRole(int roleId) {
		super();
		this.roleId = roleId;
	}


	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

}
