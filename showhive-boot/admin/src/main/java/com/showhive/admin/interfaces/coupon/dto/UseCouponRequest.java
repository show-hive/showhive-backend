package com.showhive.admin.interfaces.coupon.dto;

import lombok.Builder;

@Builder
public record UseCouponRequest(Long orderId) {

}
