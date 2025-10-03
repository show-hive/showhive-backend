package com.showhive.coupon.exception;

import com.showhive.ShowHiveException;

public class CouponException extends ShowHiveException {

    public CouponException(CouponErrorCode couponErrorCode) {
        super(couponErrorCode.getMessage(), couponErrorCode.getStatusCode());
    }
}
