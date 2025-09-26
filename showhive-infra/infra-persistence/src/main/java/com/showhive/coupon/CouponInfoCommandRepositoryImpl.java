package com.showhive.coupon;

import com.showhive.coupon.command.CouponInfoCommandJpaRepository;
import com.showhive.coupon.domain.CouponInfo;
import com.showhive.coupon.repository.command.CouponInfoCommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CouponInfoCommandRepositoryImpl implements CouponInfoCommandRepository {

    private final CouponInfoCommandJpaRepository couponInfoCommandJpaRepository;

    @Override
    public CouponInfo create(CouponInfo couponInfo) {
        couponInfoCommandJpaRepository.save(couponInfo);
        return couponInfo;
    }
}
