package com.showhive.venue.exception;

import lombok.Getter;

@Getter
public enum SeatErrorCode {

    //4XX
    SEAT_NOT_FOUND(404, "존재하지 않는 좌석입니다."),
    SEAT_TYPE_NOT_VALID(400, "유효하지 않은 좌석 유형입니다."),
    SEAT_GRADE_NOT_VALID(400, "유효하지 않은 좌석 등급입니다."),
    SEAT_COLUMN_INVALID(400, "좌석 열 정보가 유효하지 않습니다."),
    SEAT_ROW_INVALID(400, "좌석 행 정보가 유효하지 않습니다."),
    SEAT_FLOOR_INVALID(400, "좌석 층 정보가 유효하지 않습니다."),
    VENUE_NOT_FOUND(404, "공연장 정보가 존재하지 않습니다."),
    ;

    private final int statusCode;
    private final String message;

    SeatErrorCode(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
