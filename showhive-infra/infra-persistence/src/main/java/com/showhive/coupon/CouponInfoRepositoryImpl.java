package com.showhive.coupon;

import com.showhive.coupon.domain.Coupon;
import com.showhive.coupon.domain.CouponInfo;
import com.showhive.coupon.repository.CouponInfoRepository;
import jakarta.persistence.LockModeType;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CouponInfoRepositoryImpl implements CouponInfoRepository {

    private final JpaCouponInfoRepository jpaCouponInfoRepository;

    @Override
    public void save(CouponInfo couponInfo) {
        jpaCouponInfoRepository.save(couponInfo);
    }

    @Override
    public Optional<CouponInfo> findByIdWithLock(Long id) {
        return jpaCouponInfoRepository.findByIdWithLock(id);
    }
}
