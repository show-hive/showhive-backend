package com.showhive.admin.application.command.usecase.couponInfo;

import com.showhive.coupon.domain.CouponInfo;
import java.util.List;

public interface GetCouponInfoUseCase {

    CouponInfo get(Long id);

    List<CouponInfo> getAll();
}
