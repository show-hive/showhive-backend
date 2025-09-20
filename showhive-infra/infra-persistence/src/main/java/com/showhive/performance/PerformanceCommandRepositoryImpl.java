package com.showhive.performance;

import com.showhive.performance.command.PerformanceCommandJpaRepository;
import com.showhive.performance.domain.Performance;
import com.showhive.performance.repository.command.PerformanceCommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PerformanceCommandRepositoryImpl implements PerformanceCommandRepository {
    private final PerformanceCommandJpaRepository commandRepository;

    @Override
    public Performance savePerformance(Performance performance) {
        return commandRepository.save(performance);
    }
}
