package com.showhive.coupon.repository.query;

import com.showhive.coupon.domain.Coupon;
import com.showhive.coupon.domain.CouponInfo;
import com.showhive.coupon.domain.Status;
import jakarta.persistence.LockModeType;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CouponQueryRepository {

    Optional<Coupon> findById(Long id);

    // 아이디와 유저아이디를 가지고 발급된 쿠폰을 찾아옴
    Optional<Coupon> findByIdAndMemberId(Long id, Long memberId);

    // 특정 정책id로 발급된 쿠폰의 개수를 확인
    @Query("SELECT COUNT(c) FROM Coupon c WHERE c.couponInfo.id = :infoId")
    Long countByCouponInfoId(@Param("infoId") Long infoId);

    // 특정 유저가 최근에 발급받은 쿠폰 리스트를 가지고 오는 인터페이스
    Page<Coupon> findByMemberIdAndStatusOrderByCreatedAtDesc(Long memberId, Status status, Pageable pageable);

    // 쓰기 락, 비관적 락 (동시성 문제 방지)
    // 다수의 사용자가 동시에 쿠폰 정책의 재고를 감소시키는 경우에 사용
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT c FROM Coupon c where c.id=:id")
    Optional<Coupon> findByIdWithLock(Long id);
}
