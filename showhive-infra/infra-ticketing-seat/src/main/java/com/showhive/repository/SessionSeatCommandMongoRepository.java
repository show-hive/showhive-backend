package com.showhive.repository;

import com.showhive.document.SessionSeatDocument;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SessionSeatCommandMongoRepository extends MongoRepository<SessionSeatDocument, String> {
}
