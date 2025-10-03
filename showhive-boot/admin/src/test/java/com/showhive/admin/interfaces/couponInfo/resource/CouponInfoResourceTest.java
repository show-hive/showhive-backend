package com.showhive.admin.interfaces.couponInfo.resource;

import com.showhive.admin.interfaces.BaseResourceTest;
import com.showhive.admin.interfaces.couponInfo.dto.CreateCouponInfoRequest;
import com.showhive.coupon.domain.CouponInfo;
import com.showhive.member.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CouponInfoResourceTest extends BaseResourceTest {

    private String accessToken;

    @BeforeEach
    void auth() {
        Member member = memberGenerator.generateAdmin("admin");
        accessToken = tokenManager.createAccessToken(member);
    }

//    @DisplayName("쿠폰 정보를 생성할 수 있다.")
//    @Test
//    void create_couponInfo() {
//        CreateCouponInfoRequest couponInfoRequest = new CouponInfo();
//    }
}
