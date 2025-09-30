package com.showhive.admin.application.command.dto.coupon;

import com.showhive.admin.interfaces.coupon.dto.CreateCouponRequest;
import com.showhive.coupon.domain.CouponStatus;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record CouponDto(

        String name,

        Integer usableCount,

        CouponStatus couponStatus,

        LocalDateTime issuedAt,

        LocalDateTime usedAt

) {
    public static CouponDto of(CreateCouponRequest request) {
        return CouponDto.builder()
                .name(request.name())
                .usableCount(request.usableCount())
                .status(CouponStatus.AVAILABLE)
                .issuedAt(request.issuedAt())
                .usedAt(request.usedAt())
                .build();
    }
}
