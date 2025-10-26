package com.showhive.performance_seat.exception;

import lombok.Getter;

@Getter
public enum PerformanceSeatErrorCode {

    //4XX
    ALREADY_SOLD_SEAT(400, "이미 예약 중이거나 예약 된 좌석입니다."),
    SEAT_NOT_CONFIRM_SOLD(400, "홀드 상태인 좌석만 예매 확정할 수 있습니다."),
    BOOKED_TIME_NOT_VALID(400, "종료일은 시작일보다 이를 수 없습니다"),
    ;

    private final int statusCode;
    private final String message;

    PerformanceSeatErrorCode(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
