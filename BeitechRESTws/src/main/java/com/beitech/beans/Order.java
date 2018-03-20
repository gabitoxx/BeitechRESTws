package com.beitech.beans;

import java.util.Calendar;
import java.util.List;

public class Order {
	
	private int id;
	private String deliveryAddress;
	private Customer customer;
	private List<OrderDetail> detail;
	private Calendar orderDate;
	
	
	public Order() {
		super();
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<OrderDetail> getDetail() {
		return detail;
	}
	public void setDetail(List<OrderDetail> detail) {
		this.detail = detail;
	}
	
	public Calendar getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Calendar orderDate) {
		this.orderDate = orderDate;
	}
	
	/***/
	public void addOrderDetail(OrderDetail detail) {
		getDetail().add(detail);
	}
}