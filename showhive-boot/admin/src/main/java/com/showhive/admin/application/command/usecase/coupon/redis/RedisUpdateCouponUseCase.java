package com.showhive.admin.application.command.usecase.coupon.redis;

import com.showhive.coupon.domain.Coupon;

public interface RedisUpdateCouponUseCase {

    void updateCouponStatus(Coupon coupon);
}
