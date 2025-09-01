package com.showhive.exception.performance;

import lombok.Getter;

@Getter
public enum PerformanceErrorCode {

    //4XX
    PERFORMANCE_NOT_FOUND(400, "존재하지 않는 공연입니다."),
    ;

    private final int statusCode;
    private final String message;

    PerformanceErrorCode(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
