package com.showhive.performance;

import com.showhive.performance.domain.PerformanceCategoryMap;
import com.showhive.performance.mapper.PerformanceCategoryMapper;
import com.showhive.performance.query.PerformanceCategoryMapQueryJpaRepository;
import com.showhive.performance.repository.query.PerformanceCategoryMapQueryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PerformanceCategoryMapQueryRepositoryImpl implements PerformanceCategoryMapQueryRepository {
    private final PerformanceCategoryMapQueryJpaRepository jpaRepository;
    private final PerformanceCategoryMapper performanceCategoryMapper;

    @Override
    public PerformanceCategoryMap findByCategoryId(Long categoryId) {
        return performanceCategoryMapper.toDomain(jpaRepository.findByCategory_Id(categoryId));
    }

    @Override
    public List<PerformanceCategoryMap> findByCategoryIds(List<Long> categoryIds) {
        return jpaRepository.findAllByCategory_IdIn(categoryIds)
                .stream()
                .map(performanceCategoryMapper::toDomain)
                .toList();
    }

    @Override
    public List<PerformanceCategoryMap> findByPerformanceId(Long performanceId) {
        return jpaRepository.findAllByPerformanceId(performanceId)
                .stream()
                .map(performanceCategoryMapper::toDomain)
                .toList();
    }
}
