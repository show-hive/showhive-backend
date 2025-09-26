package com.showhive.venue.exception;

import lombok.Getter;

@Getter
public enum SeatGradeErrorCode {

    //4XX
    SEAT_GRADE_NOT_FOUND(404, "존재하지 않는 좌석 등급입니다."),
    SEAT_GRADE_NOT_VALID(400, "유효하지 않은 좌석 등급입니다."),
    SEAT_GRADE_SORT_CONDITION_NOT_VALID(400, "유효하지 않은 정렬 조건입니다."),
    SEAT_GRADE_DIRECTION_NOT_VALID(400, "유효하지 않은 정렬 방향입니다.");

    private final int statusCode;
    private final String message;

    SeatGradeErrorCode(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
