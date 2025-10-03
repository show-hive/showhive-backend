package com.showhive.coupon.exception;

import lombok.Getter;

@Getter
public enum CouponErrorCode {

    //4XX

    COUPON_NOT_FOUND(400, "존재하지 않는 쿠폰입니다."),
    COUPON_NOT_USED(400, "사용되지 않은 쿠폰입니다."),
    COUPON_ISSUED(400, "이미 발행된 쿠폰입니다."),
    COUPON_ALREADY_USED(400, "이미 사용된 쿠폰입니다"),
    COUPON_EXPIRED(400, "만료된 쿠폰입니다"),
    COUPON_NOT_PERIOD(400, "쿠폰 발급 기간이 아닙니다."),
    COUPON_INFO_NOT_FOUND(400, "찾을 수 없는 쿠폰 정보입니다."),
    COUPON_ISSUE_ERROR(400, "쿠폰 발급 중 오류가 발생했습니다."),
    COUPON_UPDATE_ERROR(400, "쿠폰 상태 업데이트 중 오류가 발생했습니다."),
    TOO_MANY_COUPON_REQUEST(400,"쿠폰 발급 요청이 많아 처리할 수 없습니다. 잠시 후 다시 시도해주세요."),
    COUPON_ALL_USED(400, "쿠폰이 모두 소진되었습니다."),
    UNAUTHORIZED_MEMBER(401, "접근 권한이 없습니다.");


    private final int statusCode;
    private final String message;

    CouponErrorCode(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
