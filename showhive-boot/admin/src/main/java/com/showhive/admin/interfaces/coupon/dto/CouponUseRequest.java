package com.showhive.admin.interfaces.coupon.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
public record CouponUseRequest(Long orderId) {

}
