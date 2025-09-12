package com.showhive.admin.application.command.usecase.impl;

import com.showhive.admin.application.command.dto.CreatePerformanceDto;
import com.showhive.admin.application.command.usecase.CreatePerformanceUseCase;
import com.showhive.admin.application.command.validator.PerformanceValidator;
import com.showhive.admin.application.mapper.PerformanceMapper;
import com.showhive.performance.domain.Performance;
import com.showhive.performance.repository.AdminPerformanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePerformanceUseCaseImpl implements CreatePerformanceUseCase {
    private final AdminPerformanceRepository performanceRepository;
    private final PerformanceValidator validator;

    @Override
    public void handle(CreatePerformanceDto commandDto) {
        validator.validate(commandDto);

        Performance performance = PerformanceMapper.INSTANCE.create(commandDto);

        performanceRepository.save(null);
    }
}
