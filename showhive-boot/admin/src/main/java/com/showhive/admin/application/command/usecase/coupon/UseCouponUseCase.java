package com.showhive.admin.application.command.usecase.coupon;

import com.showhive.admin.interfaces.coupon.dto.CouponResponse;

public interface UseCouponUseCase {

    CouponResponse useCoupon(Long couponId, Long orderId);
}
