package com.zen.beans;

public class Admin extends AbstractRole {

	public Admin() {
		super();
	}

	public Admin(int roleId) {
		super(roleId);
	}
	
	//Methods
	
	public void validateCategory(Category category){}
	
	public void createCategory(String name, String desc, Category parentCategory){}
	
	

}
