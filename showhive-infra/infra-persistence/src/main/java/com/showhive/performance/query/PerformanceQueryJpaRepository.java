package com.showhive.performance.query;

import com.showhive.performance.entity.PerformanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformanceQueryJpaRepository extends JpaRepository<PerformanceEntity, Long> {
    boolean existsByTitleAndVenueId(String title, Long venueId);
}
