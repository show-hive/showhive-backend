package com.showhive.admin.interfaces.coupon.dto;

import com.showhive.coupon.domain.CouponStatus;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record CreateCouponRequest(

        Long couponInfoId,

        String name,

        LocalDateTime issuedAt, // 발급일시

        LocalDateTime usedAt,   // 사용일시

        Integer usableCount,    // 사용가능횟수

        CouponStatus couponStatus
) {
}
