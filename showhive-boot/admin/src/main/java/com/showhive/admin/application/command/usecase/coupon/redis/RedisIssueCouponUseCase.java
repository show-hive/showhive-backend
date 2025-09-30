package com.showhive.admin.application.command.usecase.coupon.redis;

import com.showhive.admin.application.command.dto.coupon.CouponDto;
import com.showhive.coupon.domain.Coupon;

public interface RedisIssueCouponUseCase {

    Coupon issueCoupon(CouponDto couponDto);
}
