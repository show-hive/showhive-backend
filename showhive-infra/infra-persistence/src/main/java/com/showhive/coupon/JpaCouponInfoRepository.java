package com.showhive.coupon;

import com.showhive.coupon.domain.CouponInfo;
import jakarta.persistence.LockModeType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

public interface JpaCouponInfoRepository extends JpaRepository<CouponInfo, Long> {
    // 쓰기 락, 비관적 락 (동시성 문제 방지)
    // 다수의 사용자가 동시에 쿠폰 정책의 재고를 감소시키는 경우에 사용
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT ci FROM CouponInfo ci where ci.id=:id")
    Optional<CouponInfo> findByIdWithLock(Long id);
}
