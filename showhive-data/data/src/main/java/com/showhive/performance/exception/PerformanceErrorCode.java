package com.showhive.performance.exception;

import lombok.Getter;

@Getter
public enum PerformanceErrorCode {

    //4XX
    PERFORMANCE_NOT_FOUND(400, "존재하지 않는 회원입니다."),
    BOOKED_TIME_NOT_VALID(400, "종료일은 시작일보다 이를 수 없습니다"),
    ;

    private final int statusCode;
    private final String message;

    PerformanceErrorCode(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
