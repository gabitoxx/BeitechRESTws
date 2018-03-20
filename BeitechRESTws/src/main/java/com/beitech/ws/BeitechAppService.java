package com.beitech.ws;

import java.security.InvalidParameterException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.beitech.beans.Customer;
import com.beitech.beans.Order;
import com.beitech.beans.OrderDetail;
import com.beitech.beans.Product;
import com.beitech.utils.LoggerUtils;
import com.beitech.utils.ResultDTO;
import com.beitech.utils.StringUtils;
import com.beitech.ws.interfaces.AppServiceInterface;

import static com.beitech.ws.Queries.getCustomer;
import static com.beitech.ws.Queries.getOrdersFromCustomerByDates;
import static com.beitech.ws.Queries.getMaxOrderId;

public class BeitechAppService implements AppServiceInterface {

	
	@Override
	public Customer getCustomerById(int customerId) {
		
		LoggerUtils.debug(this, "getCustomerById() - intro");
		
		/** maybe we can use assertions later */
		if ( customerId <= 0 ) {
			throw new InvalidParameterException("Customer ID can't be negative");
		}
		
		Customer customer = new Customer();
		
		Connection conn = DatabaseConfiguration.getConnection();
		LoggerUtils.debug(this, "getCustomerById() - Connection established");
		
		String sQuery = getCustomer;

		try {
			/* Create the java statement */
			PreparedStatement statementWithArgs = conn.prepareStatement(sQuery);
			
			statementWithArgs.setInt(1, customerId);
			
			long lPerformance = System.nanoTime();
			
			/* execute the query, get a java resultset */
			ResultSet rsFromDB = statementWithArgs.executeQuery();
			
			LoggerUtils.performance("statementWithArgs.executeQuery()", System.nanoTime() - lPerformance);
			
			fetchingCustomers:
			while ( rsFromDB.next() ){
				
				customer.setId( rsFromDB.getInt("customer_id") );
				customer.setName( rsFromDB.getString("name") );
				customer.setEmail( rsFromDB.getString("email") );
		        
				break fetchingCustomers;
			}
			
		} catch (SQLException e) {
			LoggerUtils.error(this, "getCustomerById() - " + e.getMessage());
			e.printStackTrace();
		}
		
		LoggerUtils.debug(this, "getCustomerById() - exit with Customer:" + customer.getId());
		
		return customer;
	}

	
	@SuppressWarnings("unused")
	@Override
	public List<Order> findOrdersByDate(int customerId, Calendar fromDate, Calendar toDate) {
		
		LoggerUtils.debug(this, "findOrdersByDate() - intro");
		
		/** maybe we can use assertions later */
		if ( customerId <= 0 ) {
			throw new InvalidParameterException("Customer ID can't be negative");
		}
		
		Customer client = getCustomerById(customerId);
		
		List<Order> result = new ArrayList<Order>();
		
		/*
		 * fixing Query with parameters
		 */
		String sQuery = StringUtils.replaceDatesInQuery(getOrdersFromCustomerByDates, fromDate, toDate);
		LoggerUtils.debug(this, "replaceDatesInQuery() - returns: " + sQuery);
		
		Connection conn = DatabaseConfiguration.getConnection();
		LoggerUtils.debug(this, "findOrdersByDate() - Connection established");
		
		try {
			/* Create the java statement */
			PreparedStatement statementWithArgs = conn.prepareStatement(sQuery);
System.out.println("***"+client.getId());			
			//statementWithArgs.setInt(1, client.getId());
			
			long lPerformance = System.nanoTime();
			
			/* execute the query, get a java resultset */
			ResultSet rsFromDB = statementWithArgs.executeQuery();
System.out.println("---2");			
			LoggerUtils.performance("statementWithArgs.executeQuery()", System.nanoTime() - lPerformance);
			
			int iLastOrderId = 0;
			Order order = null;
			
			fetchingOrders:
			while ( rsFromDB.next() ){
System.out.println("---3");				
				int iCurrent = rsFromDB.getInt("order_id");
				
				if ( iCurrent != iLastOrderId ) {
					/* looping in another Order */
					order = new Order();
				
					order.setId( iCurrent );
					order.setDeliveryAddress( rsFromDB.getString("delivery_address") );
					order.setOrderDate( StringUtils.getCalendarFromString( rsFromDB.getString("creation_date").substring(0, 8)) );
					
					iLastOrderId = iCurrent;
				}
				
				OrderDetail detail = new OrderDetail();
				detail.setOrderId( order.getId() );
				
				Product product = new Product();
				product.setId( detail.getOrderId() );
				product.setName( rsFromDB.getString("name") );
				product.setDescription( rsFromDB.getString("product_description") );
				product.setPrice( rsFromDB.getDouble("price") );
				
				/**/
				detail.setProduct(product);
				
				/**/
				order.addOrderDetail(detail);
				
				/**/
				result.add(order);
			}
System.out.println("---4");			
		} catch (SQLException e) {
			LoggerUtils.error(this, "findOrdersByDate() - " + e.getMessage());
			e.printStackTrace();
		}
		
		LoggerUtils.debug(this, "findOrdersByDate() - exit with list size:" + result.size());
		return result;
	}


	@Override
	public ResultDTO createOrder(int customerId, String deliveryAddress, String[] productsIds) {
		
		LoggerUtils.debug(this, "createOrder() - intro");
		
		ResultDTO result = new ResultDTO();

		Connection conn = DatabaseConfiguration.getConnection();
		LoggerUtils.debug(this, "createOrder() - Connection established");
		
		int maxOrderId = maxId("order");
		int iSuccessfulChanges = 0;
		
		String sQuery = Queries.insertOrder;
		
		try {	
			/* Create the java statement */
			PreparedStatement statementWithArgs = conn.prepareStatement(sQuery);
			
			statementWithArgs.setInt(1, maxOrderId);
			statementWithArgs.setInt(2, customerId);
			statementWithArgs.setString(3, deliveryAddress);
			
			long lPerformance = System.nanoTime();
			
			/* execute the query, get a java resultset */
			iSuccessfulChanges = statementWithArgs.executeUpdate();
			
			LoggerUtils.performance("statementWithArgs.executeQuery()", System.nanoTime() - lPerformance);
			
			if ( iSuccessfulChanges == 1 ) {
				/* insert details */
				iSuccessfulChanges = insertDetails(maxOrderId, productsIds);
			}
			
		} catch (SQLException e) {
			LoggerUtils.error(this, "getCustomerById() - " + e.getMessage());
			e.printStackTrace();
			return new ResultDTO(false, e.getMessage(), 0, 1);
		}
		
		LoggerUtils.debug(this, "createOrder() - exit");
		result.setSuccess(true);
		result.setSuccessfulChanges(iSuccessfulChanges);
		return result;
	}

	
	/**
	 * getting the next sequence val according to table
	 */
	@Override
	public int maxId(String tableName) {
		
		Connection conn = DatabaseConfiguration.getConnection();
		LoggerUtils.debug(this, "maxId() - Connection established");
		
		String sQuery = "";
		
		if ( tableName.equalsIgnoreCase("order") ) {
			sQuery = getMaxOrderId;
		}
		
		int result = 0;
		
		try {
			Statement statement = conn.createStatement();
			
			ResultSet rsFromDB = statement.executeQuery( sQuery );
			
			while ( rsFromDB.next() ){
				result = rsFromDB.getInt("maxId");
				break;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return (result + 1);
	}
	
	/**
	 * Order 1 - N Details
	 * @param orderId
	 * @param productIds
	 * @return
	 */
	private int insertDetails(int orderId, String[] productIds) {
		
		LoggerUtils.debug(this, "insertDetails() - intro");
		
		if ( productIds.length <= 0 ) {
			return 0;
		}
		
		int result = 0;
		String sQuery = Queries.insertOrderDetail;

		Connection conn = DatabaseConfiguration.getConnection();
		LoggerUtils.debug(this, "insertDetails() - Connection established");
		
		for ( String singleId : productIds ) {
			
			try {
				PreparedStatement statementWithArgs = conn.prepareStatement(sQuery);
				
				statementWithArgs.setInt(1, orderId);
				statementWithArgs.setInt(2, Integer.parseInt( singleId) );
				
				result += statementWithArgs.executeUpdate();
				
			} catch (SQLException e) {
				/*
				 * pending: better management of exceptions
				 */
				e.printStackTrace();
			}
		}
		
		LoggerUtils.debug(this, "insertDetails() - exit");
		return result;
	}
	
}