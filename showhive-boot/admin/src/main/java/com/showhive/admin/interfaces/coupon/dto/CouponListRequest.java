package com.showhive.admin.interfaces.coupon.dto;

import com.showhive.coupon.domain.Status;
import lombok.Builder;

@Builder
public record CouponListRequest(Status status, Integer page, Integer size) { }
