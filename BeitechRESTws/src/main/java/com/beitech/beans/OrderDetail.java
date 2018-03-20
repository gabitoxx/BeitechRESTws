package com.beitech.beans;

public class OrderDetail {
	
	
	private int orderId;
	
	/* Hibernate style */
	private Product product;
	
	/* plain style */
	private int productId;
	
	public OrderDetail() {
		super();
	}
	
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}


	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
}