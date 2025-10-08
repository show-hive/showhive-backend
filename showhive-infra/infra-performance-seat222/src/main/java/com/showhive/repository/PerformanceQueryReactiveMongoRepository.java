package com.showhive.repository;

import com.showhive.document.SeatDocument;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PerformanceQueryReactiveMongoRepository extends
        ReactiveMongoRepository<SeatDocument, ObjectId> {
}
