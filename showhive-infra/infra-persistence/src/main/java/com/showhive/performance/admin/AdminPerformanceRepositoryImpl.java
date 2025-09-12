package com.showhive.performance.admin;

import com.showhive.performance.domain.Performance;
import com.showhive.performance.repository.AdminPerformanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AdminPerformanceRepositoryImpl implements AdminPerformanceRepository {
    private final JpaAdminPerformanceRepository performanceRepository;

    @Override
    public boolean existsByTitleAndVenue(String title, Long venueId) {
        return performanceRepository.existsByTitleAndVenue(title, venueId);
    }

    @Override
    public Performance save(Performance performance) {
        return performanceRepository.save(performance);
    }
}
