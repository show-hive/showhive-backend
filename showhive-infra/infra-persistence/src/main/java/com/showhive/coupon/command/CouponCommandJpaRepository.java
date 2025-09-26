package com.showhive.coupon.command;

import com.showhive.coupon.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponCommandJpaRepository extends JpaRepository<Coupon, Long> {
}
