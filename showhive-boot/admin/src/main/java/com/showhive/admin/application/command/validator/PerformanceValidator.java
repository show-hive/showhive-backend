package com.showhive.admin.application.command.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * TODO 2개 이상의 도메인 검증이 필요할 때 Validator 사용
 */
@Component
@RequiredArgsConstructor
public class PerformanceValidator {

    /*public void validate(CreatePerformanceDto command) {
            if (performanceRepository.existsByTitleAndVenue(command.title(), command.venueId())) {
            throw new IllegalArgumentException("같은 날짜에 이미 공연이 존재합니다.");
        }
    }*/

}
