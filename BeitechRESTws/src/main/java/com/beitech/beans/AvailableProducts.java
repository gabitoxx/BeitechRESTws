package com.beitech.beans;

public class AvailableProducts {
	
	/* filling Hibernate style */
	private Customer customer;
	private Product product;
	
	/* filling plain style */
	private int customerId;
	private int productId;
	
	public AvailableProducts() {
		super();
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}


	public int getCustomerId() {
		return customerId;
	}


	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}


	public int getProductId() {
		return productId;
	}


	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	
}