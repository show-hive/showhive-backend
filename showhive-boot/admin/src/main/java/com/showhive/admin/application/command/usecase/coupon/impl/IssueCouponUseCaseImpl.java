package com.showhive.admin.application.command.usecase.coupon.impl;

import com.showhive.admin.application.command.usecase.coupon.IssueCouponUseCase;
import com.showhive.admin.application.command.usecase.coupon.redis.RedisIssueCouponUseCase;
import com.showhive.admin.application.command.usecase.coupon.redis.RedisUpdateCouponUseCase;
import com.showhive.admin.interfaces.coupon.dto.CreateCouponRequest;
import com.showhive.admin.interfaces.coupon.dto.CouponResponse;
import com.showhive.coupon.domain.Coupon;
import com.showhive.coupon.exception.CouponErrorCode;
import com.showhive.coupon.exception.CouponException;
import com.showhive.coupon.repository.query.CouponQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class IssueCouponUseCaseImpl implements IssueCouponUseCase {

    private final CouponQueryRepository couponQueryRepository;
    private final RedisIssueCouponUseCase redisIssueCouponUseCase;
    private final RedisUpdateCouponUseCase redisUpdateCouponUseCase;

    // 쿠폰 발급
    @Transactional
    @Override
    public CouponResponse issueCoupon(CreateCouponRequest request) {
        Coupon coupon = redisIssueCouponUseCase.issueCoupon(request);
        redisUpdateCouponUseCase.updateCouponStatus(couponQueryRepository.findById(coupon.getId())
                .orElseThrow(() -> new CouponException(CouponErrorCode.COUPON_NOT_FOUND)));

        return CouponResponse.from(coupon);
    }
}
