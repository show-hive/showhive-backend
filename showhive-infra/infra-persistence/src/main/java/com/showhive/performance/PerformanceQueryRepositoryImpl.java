package com.showhive.performance;

import com.showhive.performance.query.PerformanceQueryJpaRepository;
import com.showhive.performance.repository.query.PerformanceQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PerformanceQueryRepositoryImpl implements PerformanceQueryRepository {
    private final PerformanceQueryJpaRepository performanceQueryRepository;

    @Override
    public boolean existsByTitleAndVenueId(String title, Long venueId) {
        return performanceQueryRepository.existsByTitleAndVenueId(title, venueId);
    }
}
