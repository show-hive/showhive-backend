package com.showhive.admin.application.command.dto.coupon;

import lombok.Builder;

@Builder
public record CouponUseRequest(Long orderId) {
}
