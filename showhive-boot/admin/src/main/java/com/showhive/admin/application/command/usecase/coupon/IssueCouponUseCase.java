package com.showhive.admin.application.command.usecase.coupon;

import com.showhive.admin.application.command.dto.coupon.CouponDto;
import com.showhive.admin.interfaces.coupon.dto.CouponIssueRequest;
import com.showhive.admin.interfaces.coupon.dto.CouponResponse;
import org.springframework.stereotype.Service;

@Service
public interface IssueCouponUseCase {

    CouponResponse issueCoupon(CouponIssueRequest request);
}
