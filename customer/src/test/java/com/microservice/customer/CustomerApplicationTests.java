package com.microservice.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;


import com.model.Customer;
import com.service.CustomerService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class CustomerApplicationTests {
	
	@Autowired
	CustomerService cs;
	
	public static Customer c1;
	public static Customer c2;

	@BeforeAll
	static void setup() {
		c1= new Customer(1L,"cust_email1", "password1", "name1", "cust1", 1234L, "dallas");
		c2= new Customer(2L,"cust_email2", "password2", "name2", "cust2", 1234L, "austin");
		
	}
	
	@Test
	@Order(1)    
	void testAddCustomer() {
		cs.addCustomer(c1);
		cs.addCustomer(c2);
		assertEquals(cs.findCustomerByUserName("cust1").isPresent(), true);
		assertEquals(cs.findCustomerByUserName("cust2").isPresent(), true);
	}
	
	@Test
	@Order(2)    
	void testLogin() {
		Customer cus = cs.logIn("cust1", "password1");
		assertEquals(cus.getEmail(), c1.getEmail());
		assertEquals(cus.getName(), c1.getName());
		assertEquals(cus.getPhoneNumber(), c1.getPhoneNumber());
		assertEquals(cus.getAddress(), c1.getAddress());
	}
	
	@Test
	@Order(3)    
	void testResetPasswordWithCorrectCreds() {
		String result = cs.resetPassword("cust1", "cust_email1", "newpassword1");
		assertEquals(result, "PASSWORDRESET");
		Optional<Customer> c = cs.findCustomerByUserName("cust1");
		assertEquals(c.get().getPassword(), "newpassword1");
		
	}
	
	@Test
	@Order(4)    
	void testResetPasswordWithIncorrectCreds() {
		String result = cs.resetPassword("cust2", "cust_email2a", "newpassword2");
		assertEquals(result, "PASSWORDRESETFAILED");
		Optional<Customer> c = cs.findCustomerByUserName("cust2");
		assertEquals(c.get().getPassword(), "password2");
		
	}
	

}
