package com.showhive.admin.application.command.usecase.impl;

import com.showhive.admin.application.command.dto.CreatePerformanceDto;
import com.showhive.admin.application.command.usecase.CreatePerformanceUseCase;
import com.showhive.performance.domain.Performance;
import com.showhive.performance.repository.AdminPerformanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePerformanceUseCaseImpl implements CreatePerformanceUseCase {
    private final AdminPerformanceRepository performanceRepository;

    @Override
    public void handle(CreatePerformanceDto commandDto) {
        // TODO venue null로 되어있는 부분 채우기 및 검증 할 부분이 ..있을까?

        Performance performance = Performance.create(commandDto.title(), null, commandDto.runningTime().getSeconds(),
                commandDto.ageRating(),
                commandDto.advantage(), commandDto.performanceInfo(), commandDto.bookStartedAt(),
                commandDto.bookEndedAt());

        performanceRepository.save(performance);
    }
}
