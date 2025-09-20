package com.showhive.coupon.domain;

import com.showhive.BaseEntity;
import com.showhive.member.domain.Grade;
import com.showhive.member.domain.Member;
import com.showhive.member.domain.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "coupon")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Coupon extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_info_id")
    private CouponInfo couponInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "issued_member_id")
    private Member member;          // 쿠폰이 발급된 사용자 id

    private String name;            // 쿠폰 이름

    private LocalDateTime issuedAt;   // 발급 일시

    private LocalDateTime usedAt;   // 사용 일시

    private Integer usableCount; // 쿠폰 사용 가능 횟수

    @Enumerated(EnumType.STRING)
    private Status status;          // 쿠폰 상태

    // TODO : 필요할 수도 있어서 erd에는 없지만 일단 넣음
    private String couponCode;      // 쿠폰 코드
    private Long orderId;           // 주문 번호

    public static Coupon create(Member member,CouponInfo couponInfo, Long userId, String name, String couponCode) {
        return Coupon.builder()
                .couponInfo(couponInfo)
                .member(member)
                .name(name)
                .couponCode(couponCode)
                .status(Status.AVAILABLE)
                .build();
    }
}
