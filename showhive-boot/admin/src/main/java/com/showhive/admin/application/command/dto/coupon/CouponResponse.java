package com.showhive.admin.application.command.dto.coupon;

import com.showhive.coupon.domain.Coupon;
import com.showhive.coupon.domain.DiscountType;
import com.showhive.coupon.domain.Status;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CouponResponse(
        Long id,
        Long memberId,
        String name,
        String couponCode,
        Integer usableCount,
        Status status,
        Long orderId,
        LocalDateTime issuedAt,
        LocalDateTime usedAt
) {
    public static CouponResponse from(Coupon coupon) {
        return CouponResponse.builder()
                .id(coupon.getId())
                .memberId(coupon.getMember() != null ? coupon.getMember().getId() : null)
                .name(coupon.getName())
                .couponCode(coupon.getCouponCode())
                .usableCount(coupon.getUsableCount())
                .status(coupon.getStatus())
                .orderId(coupon.getOrderId())
                .issuedAt(coupon.getIssuedAt())
                .usedAt(coupon.getUsedAt())
                .build();
    }
}
