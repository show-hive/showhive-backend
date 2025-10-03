package com.showhive.admin.interfaces.couponInfo.dto;

import com.showhive.coupon.domain.CouponInfo;
import com.showhive.coupon.domain.CouponType;
import com.showhive.coupon.domain.DiscountType;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record CouponInfoResponse(
        Long id,
        DiscountType discountType,
        String name,
        String description,
        Integer issuedCount,
        Integer discountValue,
        Integer discountRate,
        Integer minPaymentAmount,
        Integer maxDiscountAmount,
        CouponType couponType,
        Integer isDuplicateUse,
        LocalDateTime startTime,
        LocalDateTime endTime,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    // Entity -> DTO
    public static CouponInfoResponse from(CouponInfo info) {
        return CouponInfoResponse.builder()
                .id(info.getId())
                .discountType(info.getDiscountType())
                .name(info.getName())
                .description(info.getDescription())
                .issuedCount(info.getIssuedCount())
                .discountValue(info.getDiscountValue())
                .discountRate(info.getDiscountRate())
                .minPaymentAmount(info.getMinPaymentAmount())
                .maxDiscountAmount(info.getMaxDiscountAmount())
                .couponType(info.getCouponType())
                .isDuplicateUse(info.getIsDuplicateUse())
                .startTime(info.getStartTime())
                .endTime(info.getEndTime())
                .createdAt(info.getCreatedAt())
                .updatedAt(info.getUpdatedAt())
                .build();
    }
}
