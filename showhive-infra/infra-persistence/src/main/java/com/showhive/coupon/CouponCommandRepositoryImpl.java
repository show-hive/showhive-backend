package com.showhive.coupon;

import com.showhive.coupon.command.CouponCommandJpaRepository;
import com.showhive.coupon.domain.Coupon;
import com.showhive.coupon.repository.command.CouponCommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CouponCommandRepositoryImpl implements CouponCommandRepository {

    private final CouponCommandJpaRepository couponCommandJpaRepository;

    @Override
    public Coupon issue(Coupon coupon) {
       return couponCommandJpaRepository.save(coupon);
    }
}
