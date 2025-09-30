package com.showhive.admin.interfaces.coupon.dto;

import com.showhive.coupon.domain.CouponStatus;
import lombok.Builder;

@Builder
public record CouponListRequest(CouponStatus couponStatus, Integer page, Integer size) { }
