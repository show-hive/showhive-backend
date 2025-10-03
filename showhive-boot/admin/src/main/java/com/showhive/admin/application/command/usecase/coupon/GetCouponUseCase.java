package com.showhive.admin.application.command.usecase.coupon;

import com.showhive.admin.interfaces.coupon.dto.CouponResponse;

public interface GetCouponUseCase {
    CouponResponse getCoupon(Long couponId);
}
