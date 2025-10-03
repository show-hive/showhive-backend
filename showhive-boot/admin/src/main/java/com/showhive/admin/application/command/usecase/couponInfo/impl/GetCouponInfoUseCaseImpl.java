package com.showhive.admin.application.command.usecase.couponInfo.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.showhive.admin.application.command.usecase.couponInfo.GetCouponInfoUseCase;
import com.showhive.coupon.domain.CouponInfo;
import com.showhive.coupon.exception.CouponErrorCode;
import com.showhive.coupon.exception.CouponException;
import com.showhive.coupon.repository.query.CouponInfoQueryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class GetCouponInfoUseCaseImpl implements GetCouponInfoUseCase {

    private final CouponInfoQueryRepository couponInfoQueryRepository;
    private final RedissonClient redissonClient;
    private final ObjectMapper objectMapper;

    private static final String COUPON_QUANTITY_KEY = "coupon:quantity:";
    private static final String COUPON_POLICY_KEY = "coupon:policy:";

    @Override
    public CouponInfo get(Long id) {
        String policyKey = COUPON_POLICY_KEY + id;
        RBucket<String> bucket = redissonClient.getBucket(policyKey);
        String policyJson = bucket.get();
        if (policyJson != null) {
            try {
                return objectMapper.readValue(policyJson, CouponInfo.class);
            } catch (JsonProcessingException e) {
                log.error("쿠폰 정책 정보를 JSON으로 파싱하는 중 오류가 발생했습니다.", e);
            }
        }
        // 1차적으로 redis에서 먼저 찾고, 없으면 아래와 같이 db에서 쿠폰을 찾는다.

        return couponInfoQueryRepository.findById(id)
                .orElseThrow(() -> new CouponException(CouponErrorCode.COUPON_INFO_NOT_FOUND));
    }

    @Override
    public List<CouponInfo> getAll() {
        return couponInfoQueryRepository.getAll();
    }
}
