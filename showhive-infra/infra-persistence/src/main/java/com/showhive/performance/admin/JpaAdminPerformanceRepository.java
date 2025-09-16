package com.showhive.performance.admin;

import com.showhive.performance.domain.Performance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaAdminPerformanceRepository extends JpaRepository<Performance,Long> {
    boolean existsByTitleAndVenueId(String title, Long venueId);
}
