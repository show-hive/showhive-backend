package com.showhive.performance.repository.query;

public interface PerformanceQueryRepository {
    boolean existsByTitleAndVenueId(String title, Long venueId);
}
