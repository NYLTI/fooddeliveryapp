package com.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Customer;
import com.repo.CustomerRepo;

@Service
public class CustomerService {
	@Autowired
	CustomerRepo customerRepo;
	
	public Optional<Customer> findCustomerByUserName(String name) {
		return customerRepo.findCustomerByUserName(name);
	}
	public Optional<Customer> findCustomerById(Long id) {
		return customerRepo.findById(id);
	}	
	public String addCustomer(Customer customer) {
		if(findCustomerByUserName(customer.getUserName()).isPresent()) {
			return "USERALREADYEXIST";
		}else {
			customerRepo.save(customer);
			return "REGISTERED";
		}
	}
	public Customer logIn(String userName, String password) {
		for(Customer cs : customerRepo.findAll()) {
			if(userName.equals(cs.getUserName()) && password.equals(cs.getPassword())) {
				return cs;
			}
		}
		return null;
	}
	public String resetPassword(String userName, String email, String password) {
		for(Customer cs : customerRepo.findAll()) {
			if(userName.equals(cs.getUserName()) && email.equals(cs.getEmail())) {
				cs.setPassword(password);
				customerRepo.save(cs);
				return "PASSWORDRESET";
			}
		}
		return "PASSWORDRESETFAILED";
	}
}
