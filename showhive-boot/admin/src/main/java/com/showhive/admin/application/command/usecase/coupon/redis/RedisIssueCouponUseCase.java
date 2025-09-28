package com.showhive.admin.application.command.usecase.coupon.redis;

import com.showhive.admin.interfaces.coupon.dto.CreateCouponRequest;
import com.showhive.coupon.domain.Coupon;

public interface RedisIssueCouponUseCase {

    Coupon issueCoupon(CreateCouponRequest request);
}
