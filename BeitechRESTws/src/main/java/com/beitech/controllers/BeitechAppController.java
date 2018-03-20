package com.beitech.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.beitech.beans.Customer;
import com.beitech.beans.Order;
import com.beitech.beans.Product;

import com.beitech.utils.LoggerUtils;
import com.beitech.utils.ResultDTO;

import static com.beitech.utils.StringUtils.getCalendarFromString;

import com.beitech.ws.BeitechAppService;

import hello.Greeting;

@RestController
public class BeitechAppController {
	
	private final AtomicLong counterFromInstance = new AtomicLong();
	
	@RequestMapping(value = "find3", method = RequestMethod.GET)
    public Greeting greeting2(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counterFromInstance.incrementAndGet(),
                "hey3 " + name);
    }
	

	@RequestMapping(value = "findProductsTest", method = RequestMethod.GET)
	
    public List<Product> findAllProducts() {
        
		List<Product> result = new ArrayList<Product>();
		
		Product p = new Product();
		p.setId((int)counterFromInstance.incrementAndGet());
		p.setDescription("description");
		p.setName("TV");
		p.setPrice(230.0);
		
		result.add(p);
		
		p = new Product();
		p.setId((int)counterFromInstance.incrementAndGet());
		p.setDescription("new description");
		p.setName("Tablet");
		p.setPrice(250.0);
		
		result.add(p);
		
		p = new Product();
		p.setId((int)counterFromInstance.incrementAndGet());
		p.setDescription("some iOs");
		p.setName("iPhone");
		p.setPrice(655.5);
		
		result.add(p);
		
		return result;
    }
	
	@RequestMapping(value = "findCustomerById", method = RequestMethod.GET)
    public Customer getCustomerById(@RequestParam(value="id") int customerId) {
		
		try( ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext("spring-beans.xml"); ){
			
			BeitechAppService service = (BeitechAppService)context.getBean("service");
			
			Customer result = service.getCustomerById( customerId );
			
			return result;
			
		} catch (BeansException e) {
			LoggerUtils.error(this, "getCustomerById() - " + e.getMessage());
			e.printStackTrace();
		}
		// this line must not be reached
		return null;
    }
	
	/**
	 * 
	 * @param customerId
	 * @param fromDate			format: aaaaMMdd
	 * @param toDate			format: aaaaMMdd
	 * @return
	 */
	@RequestMapping(value = "findOrders", method = RequestMethod.GET)
    public List<Order> findOrdersByDate(
    		@RequestParam(value="id") int customerId,
    		@RequestParam(value="fromDate", defaultValue="") String fromDate,
    		@RequestParam(value="toDate", defaultValue="") String toDate) {
        
		try( ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext("spring-beans.xml"); ){
			
			/* instance service */
			BeitechAppService service = (BeitechAppService)context.getBean("service");
			
			/**/
			Calendar cFrom = Calendar.getInstance();
			if ( !fromDate.isEmpty()) {
				cFrom = getCalendarFromString(fromDate);
			} else {
				cFrom = null;
			}

			/**/
			Calendar cTo = Calendar.getInstance();
			if ( !toDate.isEmpty()) {
				cTo = getCalendarFromString(toDate);
			} else {
				cTo = null;
			}
			
			/* into database */
			List<Order> result = service.findOrdersByDate(customerId, cFrom, cTo);
			
			return result;
			
		} catch (BeansException e) {
			LoggerUtils.error(this, "getCustomerById() - " + e.getMessage());
			e.printStackTrace();
		}
		// this line must not be reached
		return null;
		
    }
	
	@RequestMapping(value = "findAllCustomers", method = RequestMethod.GET)
    public Customer getAllCustomers() {
        return null;
    }
	
	/**
	 * Inserting a new Order - getting data from a Form (a POST request)
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "createOrder", method = RequestMethod.POST)
    public ResponseEntity<String> createNewOrder(HttpServletRequest request) {
		
		int iCustomerId = Integer.parseInt( request.getParameter("customerId") );
		
		String sAddress = request.getParameter("deliveryAddress");
		
		String sProductsCsv = request.getParameter("productsCsv");
		
		String[] productIds = sProductsCsv.split(",");
		
		try( ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext("spring-beans.xml"); ){
			
			/* instance service */
			BeitechAppService service = (BeitechAppService)context.getBean("service");
			
			ResultDTO result = service.createOrder(iCustomerId, sAddress, productIds);
			
			HttpStatus status = ( result.isSuccess())? HttpStatus.OK: HttpStatus.UNPROCESSABLE_ENTITY;
		
			return new ResponseEntity<String>("Response from POST method:createOrder()", status);
			
		} catch (BeansException e) {
			LoggerUtils.error(this, "getCustomerById() - " + e.getMessage());
			e.printStackTrace();
		}
		// this line must not be reached
		return null;
    }
	
}