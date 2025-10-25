package com.showhive.performance.exception;

import lombok.Getter;

@Getter
public enum PerformanceErrorCode {

    //4XX
    PERFORMANCE_NOT_FOUND(404, "존재하지 않는 회원입니다."),
    BOOKED_TIME_NOT_VALID(400, "종료일은 시작일보다 이를 수 없습니다"),

    // Performance Session
    PERFORMANCE_SESSION_DUPLICATED(400, "회차가 중복 되었습니다."),
    PERFORMANCE_SESSION_NOT_FOUND(404, "회차를 찾을 수 없습니다."),

    // Casting
    CASTING_INVALID_INPUT(400, "캐스팅 정보가 누락되었습니다."),
    ;
    private final int statusCode;
    private final String message;

    PerformanceErrorCode(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
