package com.showhive.venue.exception;

import lombok.Getter;

@Getter
public enum SeatErrorCode {

    //4XX
    SEAT_GRADE_NOT_FOUND(404, "존재하지 않는 좌석입니다."),
    SEAT_TYPE_NOT_VALID(400, "유효하지 않은 좌석 유형입니다."),
    ;

    private final int statusCode;
    private final String message;

    SeatErrorCode(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
