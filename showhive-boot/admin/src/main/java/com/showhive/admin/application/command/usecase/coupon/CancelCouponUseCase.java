package com.showhive.admin.application.command.usecase.coupon;

import com.showhive.admin.interfaces.coupon.dto.CouponResponse;
import org.springframework.transaction.annotation.Transactional;

public interface CancelCouponUseCase {

    CouponResponse cancelCoupon(Long couponId);
}
