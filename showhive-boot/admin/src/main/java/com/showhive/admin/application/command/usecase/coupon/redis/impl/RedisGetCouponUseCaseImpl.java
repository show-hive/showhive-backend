package com.showhive.admin.application.command.usecase.coupon.redis.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.showhive.admin.application.command.usecase.coupon.GetCouponUseCase;
import com.showhive.admin.application.command.usecase.coupon.redis.RedisGetCouponUseCase;
import com.showhive.admin.interfaces.coupon.dto.CouponResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RedisGetCouponUseCaseImpl implements RedisGetCouponUseCase {

    private final RedissonClient redissonClient;
    private final ObjectMapper objectMapper;

    private static final String COUPON_STATE_KEY = "coupon:state:";

    @Override
    public CouponResponse getCouponState(Long couponId) {
        try {
            String stateKey = COUPON_STATE_KEY + couponId;
            RBucket<String> bucket = redissonClient.getBucket(stateKey);
            String couponJson = bucket.get();

            if (couponJson == null) {
                return null;
            }

            return objectMapper.readValue(couponJson, CouponResponse.class);
        } catch (Exception e) {
            log.error("Error getting coupon state: {}", e.getMessage(), e);
            throw new RuntimeException("쿠폰 상태 조회 중 오류가 발생했습니다.", e);
        }
    }


}
