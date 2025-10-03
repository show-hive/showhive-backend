package com.showhive.admin.application.command.dto.couponInfo;

import com.showhive.admin.interfaces.couponInfo.dto.CreateCouponInfoRequest;
import com.showhive.coupon.domain.CouponType;
import com.showhive.coupon.domain.DiscountType;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record CouponInfoDto(

        String name,

        String description,

        DiscountType discountType,       // 정액/정률 등

        Integer discountValue,           // 정액 금액

        Integer discountRate,            // 정률(%)

        Integer minPaymentAmount,

        Integer maxDiscountAmount,

        CouponType couponType,           // 쿠폰 유형

        Integer issuedCount,            // 발급가능개수

        Integer isDuplicateUse,

        LocalDateTime startTime,

        LocalDateTime endTime

) {
    public static CouponInfoDto of(CreateCouponInfoRequest request) {
        return CouponInfoDto.builder()
                .name(request.name())
                .description(request.description())
                .discountType(request.discountType())
                .issuedCount(request.issuedCount())
                .discountValue(request.discountValue())
                .discountRate(request.discountRate())
                .minPaymentAmount(request.minPaymentAmount())
                .maxDiscountAmount(request.maxDiscountAmount())
                .couponType(request.couponType())
                .isDuplicateUse(request.isDuplicateUse())
                .startTime(request.startTime())
                .endTime(request.endTime())
                .build();
    }
}
