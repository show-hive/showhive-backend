package com.showhive.admin.interfaces.coupon.dto;

import com.showhive.coupon.domain.Coupon;
import com.showhive.coupon.domain.CouponInfo;
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
        DiscountType discountType,
        Integer discountValue,
        Integer usableCount,
        Status status,
        Long orderId,
        LocalDateTime issuedAt,
        LocalDateTime usedAt
) {
    public static CouponResponse from(Coupon coupon) {
        CouponInfo info = coupon.getCouponInfo();
        return CouponResponse.builder()
                .id(coupon.getId())
                .memberId(coupon.getMember() != null ? coupon.getMember().getId() : null)
                .name(coupon.getName())
                .couponCode(coupon.getCouponCode())
                .discountType(info.getDiscountType())   // 쿠폰 정보로부터 받아옴
                .discountValue(info.getDiscountValue()) // 쿠폰 정보로부터 받아옴
                .usableCount(coupon.getUsableCount())
                .status(coupon.getStatus())
                .orderId(coupon.getOrderId())
                .issuedAt(coupon.getIssuedAt())
                .usedAt(coupon.getUsedAt())
                .build();
    }
}
