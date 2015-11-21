package com.zen.beans;

import java.util.Date;

public class Category {
	
	private int categoryId;
	private String categoryName;
	private String categoryDesc;
	private Date validationDate;
	
	
	public Category() {
		super();
	}
	
	public Category(String categoryName, String categoryDesc, Date validationDate) {
		super();
		this.categoryName = categoryName;
		this.categoryDesc = categoryDesc;
		this.validationDate = validationDate;
	}


	public Category(int categoryId, String categoryName, String categoryDesc, Date validationDate) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.categoryDesc = categoryDesc;
		this.validationDate = validationDate;
	}


	public int getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}


	public String getCategoryName() {
		return categoryName;
	}


	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}


	public String getCategoryDesc() {
		return categoryDesc;
	}


	public void setCategoryDescr(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}


	public Date getValidationDate() {
		return validationDate;
	}


	public void setValidationDate(Date validationDate) {
		this.validationDate = validationDate;
	}
	
	

}
