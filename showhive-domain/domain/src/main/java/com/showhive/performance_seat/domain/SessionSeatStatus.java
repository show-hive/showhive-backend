package com.showhive.performance_seat.domain;

import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SessionSeatStatus {
    AVAILABLE("사용 가능"),
    HOLD("예매 중"),
    SOLD("예매 완료"),
    ;

    private final String description;

    public static SessionSeatStatus fromString(String description) {
        return Arrays.stream(SessionSeatStatus.values())
                .filter(sessionSeatStatus ->  sessionSeatStatus.name().equals(description))
                .findFirst()
                .orElse(SessionSeatStatus.AVAILABLE);
    }
}
