package com.showhive.coupon.command;

import com.showhive.coupon.domain.CouponInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponInfoCommandJpaRepository extends JpaRepository<CouponInfo, Long> {
}
