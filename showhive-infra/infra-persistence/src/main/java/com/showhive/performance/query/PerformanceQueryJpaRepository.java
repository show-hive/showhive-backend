package com.showhive.performance.query;

import com.showhive.performance.domain.Performance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformanceQueryJpaRepository extends JpaRepository<Performance,Long> {
    boolean existsByTitleAndVenueId(String title, Long venueId);
}
