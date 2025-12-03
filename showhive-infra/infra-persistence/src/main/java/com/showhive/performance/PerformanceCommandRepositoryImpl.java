package com.showhive.performance;

import com.showhive.performance.command.PerformanceCommandJpaRepository;
import com.showhive.performance.domain.Performance;
import com.showhive.performance.entity.PerformanceCategoryMapEntity;
import com.showhive.performance.entity.PerformanceEntity;
import com.showhive.performance.exception.PerformanceErrorCode;
import com.showhive.performance.exception.PerformanceException;
import com.showhive.performance.mapper.PerformanceMapper;
import com.showhive.performance.query.PerformanceCategoryMapQueryJpaRepository;
import com.showhive.performance.query.PerformanceQueryJpaRepository;
import com.showhive.performance.repository.command.PerformanceCommandRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PerformanceCommandRepositoryImpl implements PerformanceCommandRepository {
    private final PerformanceCommandJpaRepository commandRepository;
    private final PerformanceQueryJpaRepository queryRepository;
    private final PerformanceCategoryMapQueryJpaRepository categoryMapQueryJpaRepository;
    private final PerformanceMapper performanceEntityMapper;
    private final PerformanceMapper performanceDomainMapper;

    @Override
    public Performance savePerformance(Performance performance) {
        PerformanceEntity performanceEntity = performanceEntityMapper.toEntity(performance);
        commandRepository.save(performanceEntity);

        List<PerformanceCategoryMapEntity> performanceCategoryMapEntities =
                categoryMapQueryJpaRepository.findAllByPerformanceId(performanceEntity.getId());

        return performanceDomainMapper.toDomain(performanceEntity, performanceCategoryMapEntities);
    }

    @Override
    public Performance findPerformanceById(Long performanceId) {
        PerformanceEntity performanceEntity = queryRepository.findById(performanceId)
                .orElseThrow(() -> new PerformanceException(PerformanceErrorCode.PERFORMANCE_NOT_FOUND));
        List<PerformanceCategoryMapEntity> performanceCategoryMapEntities =
                categoryMapQueryJpaRepository.findAllByPerformanceId(performanceId);

        return performanceDomainMapper.toDomain(performanceEntity, performanceCategoryMapEntities);
    }

    @Override
    public boolean existsPerformanceById(Long id) {
        return queryRepository.existsById(id);
    }
}
