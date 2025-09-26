package com.showhive.admin.application.command.usecase.coupon.impl;

import com.showhive.admin.application.command.usecase.coupon.GetCouponUseCase;
import com.showhive.admin.application.command.usecase.coupon.redis.RedisGetCouponUseCase;
import com.showhive.admin.application.command.usecase.coupon.redis.RedisIssueCouponUseCase;
import com.showhive.admin.application.command.usecase.coupon.redis.RedisUpdateCouponUseCase;
import com.showhive.admin.interfaces.coupon.dto.CouponResponse;
import com.showhive.coupon.domain.Coupon;
import com.showhive.coupon.exception.CouponErrorCode;
import com.showhive.coupon.exception.CouponException;
import com.showhive.coupon.repository.command.CouponCommandRepository;
import com.showhive.coupon.repository.query.CouponQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetCouponUseCaseImpl implements GetCouponUseCase {

    private final CouponCommandRepository couponCommandRepository;
    private final CouponQueryRepository couponQueryRepository;
    private final RedisIssueCouponUseCase redisIssueCouponUseCase;
    private final RedisUpdateCouponUseCase redisUpdateCouponUseCase;
    private final RedisGetCouponUseCase redisGetCouponUseCase;

    @Override
    public CouponResponse getCoupon(Long couponId) {
        CouponResponse cachedCoupon = redisGetCouponUseCase.getCouponState(couponId);
        if(cachedCoupon != null) {
            return cachedCoupon;
        }

        Coupon coupon = couponQueryRepository.findById(couponId)
                .orElseThrow(() -> new CouponException(CouponErrorCode.COUPON_NOT_FOUND));

        CouponResponse response = CouponResponse.from(coupon);
        redisUpdateCouponUseCase.updateCouponStatus(coupon);

        return response;
    }
}
