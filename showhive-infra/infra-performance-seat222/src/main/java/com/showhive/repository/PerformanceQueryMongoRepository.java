package com.showhive.repository;

import com.showhive.document.SeatDocument;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PerformanceQueryMongoRepository extends
        ReactiveMongoRepository<SeatDocument, ObjectId> {
}
