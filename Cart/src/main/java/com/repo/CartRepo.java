package com.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.model.Cart;

@Repository
public interface CartRepo extends MongoRepository<Cart, String> {

}
