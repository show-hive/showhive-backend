package com.showhive.admin.fixture;

import com.showhive.coupon.domain.CouponInfo;
import com.showhive.coupon.domain.CouponType;
import com.showhive.coupon.domain.DiscountType;
import com.showhive.coupon.repository.command.CouponInfoCommandRepository;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CouponInfoGenerator {

    @Autowired
    private CouponInfoCommandRepository couponInfoCommandRepository;

    public CouponInfo generateCouponInfo(DiscountType discountType, String name, String description,
                                         Integer issuedCount, Integer discountValue, Integer discountRate,
                                         Integer minPaymentAmount, Integer maxDiscountAmount, CouponType couponType,
                                         Integer isDuplicateUse, LocalDateTime startTime, LocalDateTime endTime) {
        CouponInfo couponInfo = CouponInfo.create(discountType, name, description, issuedCount, discountValue,
                discountRate, minPaymentAmount, maxDiscountAmount, couponType, isDuplicateUse, startTime, endTime);
        return couponInfoCommandRepository.create(couponInfo);
    }
}
