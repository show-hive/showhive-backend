package com.showhive.admin.application.command.usecase.performance.impl;

import com.showhive.admin.application.command.dto.CreatePerformanceDto;
import com.showhive.admin.application.command.usecase.performance.CreatePerformanceUseCase;
import com.showhive.performance.repository.command.PerformanceCommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePerformanceUseCaseImpl implements CreatePerformanceUseCase {
    private final PerformanceCommandRepository performanceRepository;

    @Override
    public void handle(CreatePerformanceDto commandDto) {

    }
}
