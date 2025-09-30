package com.showhive.coupon;

import com.showhive.coupon.domain.Coupon;
import com.showhive.coupon.domain.CouponStatus;
import com.showhive.coupon.query.CouponQueryJpaRepository;
import com.showhive.coupon.repository.query.CouponQueryRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CouponQueryRepositoryImpl implements CouponQueryRepository {
    private final CouponQueryJpaRepository couponQueryJpaRepository;

    @Override
    public Optional<Coupon> findById(Long id) {
        return couponQueryJpaRepository.findById(id);
    }

    @Override
    public Optional<Coupon> findByIdAndMemberId(Long id, Long memberId) {
        return couponQueryJpaRepository.findByIdAndMemberId(id, memberId);
    }

    @Override
    public Long countByCouponInfoId(Long infoId) {
        return couponQueryJpaRepository.countByCouponInfoId(infoId);
    }

    @Override
    public Page<Coupon> findByMemberIdAndStatusOrderByCreatedAtDesc(Long memberId, CouponStatus couponStatus, Pageable pageable) {
        return couponQueryJpaRepository.findByMemberIdAndStatusOrderByCreatedAtDesc(memberId, couponStatus, pageable);
    }

    @Override
    public Optional<Coupon> findByIdWithLock(Long id) {
        return couponQueryJpaRepository.findByIdWithLock(id);
    }
}
