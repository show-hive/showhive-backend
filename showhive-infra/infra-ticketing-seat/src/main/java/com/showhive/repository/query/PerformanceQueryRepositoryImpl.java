/**
package com.showhive.repository.query;

import com.showhive.performance.repository.query.PerformanceQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Qualifier("mongo")
@Repository
@RequiredArgsConstructor
public class PerformanceQueryRepositoryImpl implements PerformanceQueryRepository {
    private final MongoTemplate mongoTemplate;

    @Override
    public boolean existsByTitleAndVenueId(String title, Long venueId) {
        return false;
    }
}


 *
 */
