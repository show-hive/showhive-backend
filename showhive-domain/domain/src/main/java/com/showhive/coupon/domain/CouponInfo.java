package com.showhive.coupon.domain;

import com.showhive.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "coupon_infos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CouponInfo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_info_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private DiscountType discountType;  // 할인 유형

    private String name; // 쿠폰 정보 이름

    private String description; // 쿠폰 설명

    private Integer issuedCount; // 발급 가능 개수

    private Integer discountValue; // 할인 금액

    private Integer discountRate; // 할인율

    private Integer minPaymentAmount; // 최소 결제 금액

    private Integer maxDiscountAmount; // 최대 할인 금액

    @Enumerated(EnumType.STRING)
    private CouponType couponType;  // 쿠폰 유형

    private Integer isDuplicateUse;    // 중복 사용 여부

    // TODO : 필요할 것 같아 일단 추가해봄
    private LocalDateTime startTime;

    @Setter
    private LocalDateTime endTime;
}
