package com.showhive.admin.application.command.validator;

import com.showhive.admin.application.command.dto.CreatePerformanceDto;
import com.showhive.performance.repository.AdminPerformanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PerformanceValidator {
    private final AdminPerformanceRepository performanceRepository;

    public void validate(CreatePerformanceDto command) {
        if (performanceRepository.existsByTitleAndVenue(command.title(), command.venueId())) {
            throw new IllegalArgumentException("같은 날짜에 이미 공연이 존재합니다.");
        }
    }

}
