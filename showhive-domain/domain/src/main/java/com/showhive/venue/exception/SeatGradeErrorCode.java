package com.showhive.venue.exception;

import lombok.Getter;

@Getter
public enum SeatGradeErrorCode {

    //4XX
    SEAT_GRADE_NOT_FOUND(404, "존재하지 않는 회원입니다."),
    GRADE_NOT_VALID(400, "등급이 유효하지 않습니다.");

    private final int statusCode;
    private final String message;

    SeatGradeErrorCode(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
