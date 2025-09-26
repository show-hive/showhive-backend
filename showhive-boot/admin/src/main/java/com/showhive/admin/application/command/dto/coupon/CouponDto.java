package com.showhive.admin.application.command.dto.coupon;

import com.showhive.admin.interfaces.coupon.dto.CouponIssueRequest;
import com.showhive.coupon.domain.Status;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record CouponDto(

        String name,

        Integer usableCount,

        Status status,

        LocalDateTime issuedAt,

        LocalDateTime usedAt

) {
    public static CouponDto of(CouponIssueRequest request) {
        return CouponDto.builder()
                .name(request.name())
                .usableCount(request.usableCount())
                .status(Status.AVAILABLE)
                .issuedAt(request.issuedAt())
                .usedAt(request.usedAt())
                .build();
    }
}
