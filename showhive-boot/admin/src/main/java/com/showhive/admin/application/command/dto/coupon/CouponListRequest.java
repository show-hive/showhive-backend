package com.showhive.admin.application.command.dto.coupon;

import com.showhive.coupon.domain.Status;
import lombok.Builder;

@Builder
public record CouponListRequest(Status status, Integer page, Integer size) { }
