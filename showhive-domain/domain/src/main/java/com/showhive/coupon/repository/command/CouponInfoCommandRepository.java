package com.showhive.coupon.repository.command;

import com.showhive.coupon.domain.CouponInfo;

public interface CouponInfoCommandRepository {

    CouponInfo create(CouponInfo couponInfo);
}
