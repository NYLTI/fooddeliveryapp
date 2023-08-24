package com.repo;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.model.Vendor;

@Repository
public interface VendorRepo extends MongoRepository<Vendor, ObjectId> {
	
	public Optional<Vendor> findVendorByUserName(String userName);
	
}