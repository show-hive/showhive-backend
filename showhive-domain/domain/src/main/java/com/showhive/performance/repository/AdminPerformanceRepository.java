package com.showhive.performance.repository;

import com.showhive.performance.domain.Performance;

public interface AdminPerformanceRepository {
    boolean existsByTitleAndVenue(String title, Long venueId);
    Performance save(Performance performance);
}
