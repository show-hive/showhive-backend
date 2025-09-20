package com.showhive.coupon;

import com.showhive.coupon.domain.Coupon;
import com.showhive.coupon.domain.Status;
import com.showhive.coupon.repository.CouponRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CouponRepositoryImpl implements CouponRepository {

    private final JpaCouponRepository jpaCouponRepository;

    @Override
    public void save(Coupon coupon) {
        jpaCouponRepository.save(coupon);
    }

    @Override
    public Optional<Coupon> findByIdAndMemberId(Long id, Long memberId) {
        return jpaCouponRepository.findByIdAndMemberId(id, memberId);
    }

    @Override
    public Long countByCouponInfoId(Long infoId) {
        return jpaCouponRepository.countByCouponInfoId(infoId);
    }

    @Override
    public Page<Coupon> findByMemberIdAndStatusOrderByCreatedAtDesc(Long memberId, Status status, Pageable pageable) {
        return jpaCouponRepository.findByMemberIdAndStatusOrderByCreatedAtDesc(memberId, status, pageable);
    }

    @Override
    public Optional<Coupon> findByIdWithLock(Long id) {
        return jpaCouponRepository.findByIdWithLock(id);
    }
}
