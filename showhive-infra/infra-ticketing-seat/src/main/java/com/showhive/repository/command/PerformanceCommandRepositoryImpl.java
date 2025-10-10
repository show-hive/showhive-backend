package com.showhive.repository.command;

import com.showhive.mapper.SeatMapper;
import com.showhive.performance.entity.Performance;
import com.showhive.performance.repository.command.PerformanceCommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PerformanceCommandRepositoryImpl implements PerformanceCommandRepository {
    private final MongoTemplate mongoTemplate;
    private final SeatMapper seatMapper;

    @Override
    public Performance savePerformance(Performance performance) {
        return null;
    }
}
