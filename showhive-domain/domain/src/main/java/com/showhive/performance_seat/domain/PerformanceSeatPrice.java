package com.showhive.performance_seat.domain;

import com.showhive.performance.domain.vo.Money;
import com.showhive.venue.domain.SeatGradeDomain;

/**
 * 공연 좌석 등급 가격
 */
public record PerformanceSeatPrice(SeatGradeDomain grade, Money price) {
}
