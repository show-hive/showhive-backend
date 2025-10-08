package com.showhive.repository;

import com.showhive.document.SeatDocument;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PerformanceCommandMongoRepository extends MongoRepository<SeatDocument, ObjectId> {
}
