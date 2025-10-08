package com.showhive.performance_seat.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class SessionSeatId {
    private Long seatId;
    private Long performanceId;
}
