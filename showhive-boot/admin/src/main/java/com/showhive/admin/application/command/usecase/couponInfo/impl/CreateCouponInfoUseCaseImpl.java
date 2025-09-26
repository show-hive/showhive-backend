package com.showhive.admin.application.command.usecase.couponInfo.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.showhive.admin.application.command.dto.couponInfo.CouponInfoDto;
import com.showhive.admin.application.command.usecase.couponInfo.CreateCouponInfoUseCase;
import com.showhive.admin.interfaces.couponInfo.dto.CouponInfoResponse;
import com.showhive.coupon.domain.CouponInfo;
import com.showhive.coupon.repository.command.CouponInfoCommandRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class CreateCouponInfoUseCaseImpl implements CreateCouponInfoUseCase {
    private final CouponInfoCommandRepository couponInfoCommandRepository;
    private final RedissonClient redissonClient;
    private final ObjectMapper objectMapper;

    private static final String COUPON_QUANTITY_KEY = "coupon:quantity:";
    private static final String COUPON_POLICY_KEY = "coupon:policy:";

    @Override
    @Transactional
    public void create(CouponInfoDto commandDto) throws JsonProcessingException {
        CouponInfo couponInfo = CouponInfo.create(commandDto.discountType(), commandDto.name(),
                commandDto.description(), commandDto.issuedCount(), commandDto.discountValue(),
                commandDto.discountRate(),
                commandDto.minPaymentAmount(), commandDto.maxDiscountAmount(), commandDto.couponType(),
                commandDto.isDuplicateUse(), commandDto.startTime(), commandDto.endTime());

        CouponInfo savedInfo = couponInfoCommandRepository.create(couponInfo);

        // Redis에 초기 수량 설정
        String quantityKey = COUPON_QUANTITY_KEY + savedInfo.getId();
        RAtomicLong atomicQuantity = redissonClient.getAtomicLong(quantityKey);
        atomicQuantity.set(savedInfo.getTotalQuantity());

        // Redis에 정책 정보 저장
        String policyKey = COUPON_POLICY_KEY + savedInfo.getId();
        String policyJson = objectMapper.writeValueAsString(CouponInfoResponse.from(savedInfo));
        RBucket<String> bucket = redissonClient.getBucket(policyKey);
        bucket.set(policyJson);
    }
}
