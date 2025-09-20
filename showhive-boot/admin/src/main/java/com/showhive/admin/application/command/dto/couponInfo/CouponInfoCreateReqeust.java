package com.showhive.admin.application.command.dto.couponInfo;

import com.showhive.coupon.domain.CouponInfo;
import com.showhive.coupon.domain.CouponType;
import com.showhive.coupon.domain.DiscountType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record CouponInfoCreateReqeust(
        @NotBlank(message = "쿠폰 정보 이름은 필수입니다.")
        String name,

        String description,

        @NotNull(message = "할인 유형은 필수입니다.")
        DiscountType discountType,       // 정액/정률 등

        // 정액/정률에 따라 둘 중 하나만 쓰일 수 있으나,
        // 기본 DTO 레벨에서는 null 허용 후 서비스/Validator에서 조건 검증 권장
        @Min(value = 1, message = "할인 금액은 1 이상이어야 합니다.")
        Integer discountValue,           // 정액 금액

        @Min(value = 1, message = "할인 율은 1 이상이어야 합니다.")
        Integer discountRate,            // 정률(%)

        @Min(value = 0, message = "최소 결제 금액은 0 이상이어야 합니다.")
        Integer minPaymentAmount,

        @Min(value = 1, message = "최대 할인 금액은 1 이상이어야 합니다.")
        Integer maxDiscountAmount,

        @NotNull(message = "쿠폰 유형은 필수입니다.")
        CouponType couponType,           // 쿠폰 유형

        // 엔티티는 snake_case(issued_count)이므로 매핑 주의
        @NotNull(message = "발급 가능 개수는 필수입니다.")
        @Min(value = 1, message = "발급 가능 개수는 1 이상이어야 합니다.")
        Integer issuedCount,

        // 1/0로 사용하는 정수형(엔티티는 Integer isDuplicateUse)
        Integer isDuplicateUse,

        @NotNull(message = "시작 시간은 필수입니다.")
        LocalDateTime startTime,

        @NotNull(message = "종료 시간은 필수입니다.")
        LocalDateTime endTime
) {
    // DTO -> Entity
    public CouponInfo toEntity() {
        return CouponInfo.builder()
                .name(name)
                .description(description)
                .discountType(discountType)
                .discountValue(discountValue)
                .discountRate(discountRate)
                .minPaymentAmount(minPaymentAmount)
                .maxDiscountAmount(maxDiscountAmount)
                .couponType(couponType)
                .issuedCount(issuedCount)
                .isDuplicateUse(isDuplicateUse)
                .startTime(startTime)
                .endTime(endTime)
                .build();
    }
}
