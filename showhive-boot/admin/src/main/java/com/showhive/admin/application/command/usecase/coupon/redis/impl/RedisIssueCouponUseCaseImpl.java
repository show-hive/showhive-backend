package com.showhive.admin.application.command.usecase.coupon.redis.impl;

import com.showhive.admin.application.command.dto.coupon.CouponDto;
import com.showhive.admin.application.command.usecase.coupon.redis.RedisIssueCouponUseCase;
import com.showhive.admin.application.command.usecase.couponInfo.GetCouponInfoUseCase;
import com.showhive.coupon.domain.Coupon;
import com.showhive.coupon.domain.CouponInfo;
import com.showhive.coupon.domain.Status;
import com.showhive.coupon.exception.CouponErrorCode;
import com.showhive.coupon.exception.CouponException;
import com.showhive.coupon.repository.command.CouponCommandRepository;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class RedisIssueCouponUseCaseImpl implements RedisIssueCouponUseCase {

    private final RedissonClient redissonClient;
    private final CouponCommandRepository couponCommandRepository;
    private final GetCouponInfoUseCase getCouponInfoUseCase;

    private static final String COUPON_QUANTITY_KEY = "coupon:quantity:";
    private static final String COUPON_LOCK_KEY = "coupon:lock:";
    private static final long LOCK_WAIT_TIME = 3;
    private static final long LOCK_LEASE_TIME = 5;

    @Transactional
    public Coupon issueCoupon(CouponDto couponDto) {
        String quantityKey = COUPON_QUANTITY_KEY + couponDto.couponInfoId();
        String lockKey = COUPON_LOCK_KEY + couponDto.couponInfoId();
        RLock lock = redissonClient.getLock(lockKey);

        try {
            boolean isLocked = lock.tryLock(LOCK_WAIT_TIME, LOCK_LEASE_TIME, TimeUnit.SECONDS);
            if (!isLocked) {
                throw new CouponException(CouponErrorCode.TOO_MANY_COUPON_REQUEST);
            }

            CouponInfo couponInfo = getCouponInfoUseCase.get(couponDto.couponInfoId());

            LocalDateTime now = LocalDateTime.now();
            if (now.isBefore(couponInfo.getStartTime()) || now.isAfter(couponInfo.getEndTime())) {
                throw new CouponException(CouponErrorCode.COUPON_NOT_PERIOD);
            }

            // 수량 체크 및 감소
            RAtomicLong atomicQuantity = redissonClient.getAtomicLong(quantityKey);
            long remainingQuantity = atomicQuantity.decrementAndGet();

            if (remainingQuantity < 0) {
                atomicQuantity.incrementAndGet();
                throw new CouponException(CouponErrorCode.COUPON_ALL_USED);
            }

            // 쿠폰 발급
            return couponCommandRepository.issue(Coupon.builder()
                    .couponInfo(couponInfo)
                    .status(Status.AVAILABLE)
                    .couponCode(generateCouponCode())
                    .build());

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new CouponException(CouponErrorCode.COUPON_ISSUE_ERROR);
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    private String generateCouponCode() {
        return java.util.UUID.randomUUID().toString().substring(0, 8);
    }
}
