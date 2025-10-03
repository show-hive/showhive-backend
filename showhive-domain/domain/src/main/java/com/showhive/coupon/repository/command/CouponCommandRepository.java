package com.showhive.coupon.repository.command;

import com.showhive.coupon.domain.Coupon;

public interface CouponCommandRepository {

    Coupon issue(Coupon coupon);

}
