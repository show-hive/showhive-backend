package com.showhive.admin.application.command.usecase.coupon.impl;


import com.showhive.admin.application.command.usecase.coupon.CancelCouponUseCase;
import com.showhive.admin.application.command.usecase.coupon.redis.RedisIssueCouponUseCase;
import com.showhive.admin.application.command.usecase.coupon.redis.RedisUpdateCouponUseCase;
import com.showhive.admin.interfaces.coupon.dto.CouponResponse;
import com.showhive.coupon.domain.Coupon;
import com.showhive.coupon.exception.CouponErrorCode;
import com.showhive.coupon.exception.CouponException;
import com.showhive.coupon.repository.query.CouponQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CancelCouponUseCaseImpl implements CancelCouponUseCase {

    private final CouponQueryRepository couponQueryRepository;
    private final RedisUpdateCouponUseCase redisUpdateCouponUseCase;

    // 쿠폰 취소
    @Transactional
    @Override
    public CouponResponse cancelCoupon(Long couponId) {
       Coupon coupon = couponQueryRepository.findByIdWithLock(couponId)
               .orElseThrow(() -> new CouponException(CouponErrorCode.COUPON_NOT_FOUND));

       coupon.cancel();
       redisUpdateCouponUseCase.updateCouponStatus(coupon);

       return CouponResponse.from(coupon);
    }
}
