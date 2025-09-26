package com.showhive.coupon;

import com.showhive.coupon.domain.CouponInfo;
import com.showhive.coupon.query.CouponInfoQueryJpaRepository;
import com.showhive.coupon.repository.query.CouponInfoQueryRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CouponInfoQueryRepositoryImpl implements CouponInfoQueryRepository {

    private final CouponInfoQueryJpaRepository couponInfoQueryJpaRepository;

    @Override
    public Optional<CouponInfo> findById(Long id) {
        return couponInfoQueryJpaRepository.findById(id);
    }

    @Override
    public Optional<CouponInfo> findByIdWithLock(Long id) {
        return couponInfoQueryJpaRepository.findByIdWithLock(id);
    }

    @Override
    public List<CouponInfo> getAll() {
        return List.of();
    }
}
