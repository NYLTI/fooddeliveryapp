package com.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.model.Admin;

@Repository
public interface AdminRepo extends CrudRepository<Admin, Long> {
	
	public Optional<Admin> findAdminByUserName(String userName);
	
}
