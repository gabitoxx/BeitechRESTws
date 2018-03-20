package com.beitech.ws.interfaces;

import java.util.Calendar;
import java.util.List;

import com.beitech.beans.Customer;
import com.beitech.beans.Order;
import com.beitech.utils.ResultDTO;

public interface AppServiceInterface {
	
	public Customer getCustomerById(int customerId);
	
	public List<Order> findOrdersByDate(int customerId, Calendar fromDate, Calendar toDate);
	
	public ResultDTO createOrder(int customerId, String deliveryAddress, String[] productsIds);
	
	public int maxId(String tableName);
	
}