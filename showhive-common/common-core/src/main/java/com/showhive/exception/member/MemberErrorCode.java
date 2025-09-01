package com.showhive.exception.member;

import lombok.Getter;

@Getter
public enum MemberErrorCode {

    //4XX
    MEMBER_NOT_FOUND(400, "존재하지 않는 회원입니다."),
    ;

    private final int statusCode;
    private final String message;

    MemberErrorCode(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
