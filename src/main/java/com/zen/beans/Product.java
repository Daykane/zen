package com.zen.beans;

public class Product {
	private int productId;
	private String productName;
	private String productDescr;
	private int availableQuantity;
	private Double memberReduction;
	private int categoryProduct;

	public Product() {
		// TODO Auto-generated constructor stub
	}

	public Product(String productName, String productDescr, int availableQuantity, Double memberReduction, int categoryProduct) {
		super();
		this.productName = productName;
		this.productDescr = productDescr;
		this.availableQuantity = availableQuantity;
		this.memberReduction = memberReduction;
		this.categoryProduct = categoryProduct;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescr() {
		return productDescr;
	}

	public void setProductDescr(String productDescr) {
		this.productDescr = productDescr;
	}

	public int getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(int availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

	public Double getMemberReduction() {
		return memberReduction;
	}

	public void setMemberReduction(Double memberReduction) {
		this.memberReduction = memberReduction;
	}

	public int getCategoryProduct() {
		return categoryProduct;
	}

	public void setCategoryProduct(int categoryProduct) {
		this.categoryProduct = categoryProduct;
	}

	
	
}
