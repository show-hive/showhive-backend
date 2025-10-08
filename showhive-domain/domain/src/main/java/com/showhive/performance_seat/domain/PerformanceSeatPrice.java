package com.showhive.performance_seat.domain;

import com.showhive.performance.domain.vo.Money;
import com.showhive.venue.domain.SeatGrade;

/**
 * 공연 좌석 등급 가격
 */
public record PerformanceSeatPrice(SeatGrade grade, Money price) {
}
