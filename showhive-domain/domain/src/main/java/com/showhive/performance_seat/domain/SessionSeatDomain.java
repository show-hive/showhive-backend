package com.showhive.performance_seat.domain;

import com.showhive.performance.domain.PerformanceSessionId;
import com.showhive.performance_seat.exception.PerformanceSeatErrorCode;
import com.showhive.performance_seat.exception.PerformanceSeatException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 공연 회차별 좌석
 */
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class SessionSeatDomain {
    private SessionSeatId sessionSeatId;
    private PerformanceSessionId performanceSessionId;
    private SessionSeatStatus status;
}
