package com.showhive.performance;

import com.showhive.performance.command.PerformanceCommandJpaRepository;
import com.showhive.performance.query.PerformanceQueryJpaRepository;
import com.showhive.performance.entity.Performance;
import com.showhive.performance.repository.command.PerformanceCommandRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PerformanceCommandRepositoryImpl implements PerformanceCommandRepository {
    private final PerformanceCommandJpaRepository commandRepository;
    private final PerformanceQueryJpaRepository queryRepository;

    @Override
    public Performance savePerformance(Performance performance) {
        return commandRepository.save(performance);
    }

    @Override
    public Optional<Performance> findPerformanceById(Long id) {
        return queryRepository.findById(id);
    }

    @Override
    public boolean existsPerformanceById(Long id) {
        return queryRepository.existsById(id);
    }
}
