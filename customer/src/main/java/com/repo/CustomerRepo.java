package com.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.model.Customer;

@Repository
public interface CustomerRepo extends CrudRepository<Customer, Long> {
	public Optional<Customer> findCustomerByUserName(String userName);
}
