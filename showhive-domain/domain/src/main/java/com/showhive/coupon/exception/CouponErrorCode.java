package com.showhive.coupon.exception;

import lombok.Getter;

@Getter
public enum CouponErrorCode {

    //4XX
    COUPON_NOT_FOUND(400, "존재하지 않는 쿠폰입니다."),
    COUPON_NOT_USED(400, "사용되지 않은 쿠폰입니다."),
    COUPON_ISSUED(400, "이미 발해된 쿠폰입니다."),
    COUPON_ALREADY_USED(400, "이미 사용된 쿠폰입니다"),
    COUPON_EXPIRED(400, "만료된 쿠폰입니다"),
    COUPON_INFO_NOT_FOUND(400, "존재하지 않는 쿠폰 정보입니다."),
    UNAUTHORIZED_MEMBER(401, "접근 권한이 없습니다."),
    ;

    private final int statusCode;
    private final String message;

    CouponErrorCode(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
