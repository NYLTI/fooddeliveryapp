package com.repo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.model.FeedBack;

@Repository
public interface FeedbackRepo extends MongoRepository<FeedBack, ObjectId> {
}
