package com.showhive.admin.application.command.usecase.coupon.redis.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.showhive.admin.application.command.usecase.coupon.redis.RedisUpdateCouponUseCase;
import com.showhive.admin.interfaces.coupon.dto.CouponResponse;
import com.showhive.coupon.domain.Coupon;
import com.showhive.coupon.exception.CouponErrorCode;
import com.showhive.coupon.exception.CouponException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class RedisUpdateCouponUseCaseImpl implements RedisUpdateCouponUseCase {

    private final RedissonClient redissonClient;
    private final ObjectMapper objectMapper;

    private static final String COUPON_STATE_KEY = "coupon:state:";

    // 쿠폰 상태를 Redis에 저장
    // @param coupon 상태를 저장할 쿠폰
    // db에 저장되지 않도록 쿠폰 상태가 변경되면 redis에 쿠폰 상태 업데이트
    @Override
    public void updateCouponStatus(Coupon coupon) {
        try {
            String stateKey = COUPON_STATE_KEY + coupon.getId();
            String couponJson = objectMapper.writeValueAsString(CouponResponse.from(coupon));
            RBucket<String> bucket = redissonClient.getBucket(stateKey);
            bucket.set(couponJson);

            log.info("Coupon state updated: {}", coupon.getId());
        } catch (Exception e) {
            log.error("Error updating coupon state: {}", e.getMessage(), e);
            throw new CouponException(CouponErrorCode.COUPON_UPDATE_ERROR);
        }
    }
}
