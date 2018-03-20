package com.beitech.ws;

public class Queries {
	
	/**
	 * future improvement: reading from *.properties
	 */
	
	
	public static final String getCustomer = " SELECT * FROM beitechdb.customer WHERE customer_id = ? ";
	
	public static final String FROM_DATE_FORMAT = "aaa1-m1-d1";
	public static final String TO_DATE_FORMAT   = "aaa2-m2-d2";
	
	public static final String getOrdersFromCustomerByDates = 
			" SELECT o.order_id, o.order_id, o.delivery_address, o.creation_date, " 
			+ "  od.product_id, p.name, p.product_description, p.price " 
			+ " FROM beitechdb.order o "
			+ "  INNER JOIN beitechdb.order_detail od ON o.order_id = od.order_id "
			+ "  INNER JOIN beitechdb.product p ON od.product_id = p.product_id "
//			+ " WHERE o.customer_id = 2 "
//			+ "  AND ( o.creation_date BETWEEN '" + FROM_DATE_FORMAT + " 00:00:00' AND '" + TO_DATE_FORMAT + " 23:59:59' ) "
			+ " ORDER BY o.order_id ASC ";
	
	public static final String getMaxOrderId = " SELECT MAX(order_id) AS orderId FROM beitechdb.order ";
	
	public static final String insertOrder = " INSERT INTO beitechdb.order(order_id, customer_id, delivery_address) VALUES ( ? , ?, ?) ";
	
	public static final String insertOrderDetail = " INSERT INTO beitechdb.order_detail(order_id, product_id) VALUES ( ? , ?) ";
	
	
}