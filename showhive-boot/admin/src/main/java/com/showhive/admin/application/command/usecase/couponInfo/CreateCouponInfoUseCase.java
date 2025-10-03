package com.showhive.admin.application.command.usecase.couponInfo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.showhive.admin.application.command.dto.couponInfo.CouponInfoDto;
import com.showhive.coupon.domain.CouponInfo;

public interface CreateCouponInfoUseCase {
    void create(CouponInfoDto commandDto) throws JsonProcessingException;
}
