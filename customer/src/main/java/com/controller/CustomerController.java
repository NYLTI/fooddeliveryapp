package com.controller;

import java.util.Map;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Customer;
import com.service.CustomerService;

@Component
@RestController
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	CustomerService customerService;
	
	@PostMapping("/register")
	public String register(@RequestBody Customer customer) {
		return customerService.addCustomer(customer);
	}
	
	@PostMapping("/login")
	public Customer logIn(@RequestBody Map<String,String> customer) {
		return customerService.logIn(customer.get("userName"),customer.get("password"));
	}
	
	@GetMapping("/get/{uname}")
	public Optional<Customer> findCustomerByUserName(@PathVariable String uname) {
		return customerService.findCustomerByUserName(uname);
	}
	@GetMapping("/find/{id}")
	public Optional<Customer> findCustomerById(@PathVariable Long id) {
		return customerService.findCustomerById(id);
	}
	@PutMapping("/reset/{pass}")
	public String resetPassword(@RequestBody Map<String,String> customer,@PathVariable String pass) {
		return customerService.resetPassword(customer.get("userName"), customer.get("email"),pass);
	}
}
