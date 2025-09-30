package com.showhive.admin.application.command.usecase.coupon;

import com.showhive.admin.application.command.dto.coupon.CouponDto;
import com.showhive.admin.interfaces.coupon.dto.CreateCouponRequest;
import com.showhive.admin.interfaces.coupon.dto.CouponResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface IssueCouponUseCase {

    CouponResponse issueCoupon(CouponDto couponDto);
}
