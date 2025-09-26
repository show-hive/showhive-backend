package com.showhive.admin.application.command.usecase.coupon.redis;

import com.showhive.admin.interfaces.coupon.dto.CouponResponse;

public interface RedisGetCouponUseCase {

    CouponResponse getCouponState(Long couponId);
}
