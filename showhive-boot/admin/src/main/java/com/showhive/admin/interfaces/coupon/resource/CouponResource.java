package com.showhive.admin.interfaces.coupon.resource;

import com.showhive.admin.application.command.usecase.coupon.CancelCouponUseCase;
import com.showhive.admin.application.command.usecase.coupon.IssueCouponUseCase;
import com.showhive.admin.application.command.usecase.coupon.UseCouponUseCase;
import com.showhive.admin.interfaces.coupon.dto.CreateCouponRequest;
import com.showhive.admin.interfaces.coupon.dto.CouponResponse;
import com.showhive.admin.interfaces.coupon.dto.UseCouponRequest;
import com.showhive.auth.RequireRole;
import com.showhive.member.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("couponControllerV2")
@RequiredArgsConstructor
@RequireRole(role = Role.MANAGER)
@RequestMapping("/admin/v2/coupons")
public class CouponResource implements CouponResourceFacade {

    private final IssueCouponUseCase issueCouponUseCase;
    private final UseCouponUseCase useCouponUseCase;
    private final CancelCouponUseCase cancelCouponUseCase;

    @PostMapping("/issue")
    public ResponseEntity<CouponResponse> issueCoupon(@RequestBody CreateCouponRequest request) {
        //CouponDto commandDto = CouponDto.of(request);
        issueCouponUseCase.issueCoupon(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/{couponId}/use")
    public ResponseEntity<CouponResponse> useCoupon(@PathVariable Long couponId,
                                                    @RequestBody UseCouponRequest request) {
        return ResponseEntity.ok(useCouponUseCase.useCoupon(couponId, request.orderId()));
    }

    @PostMapping("/{couponId}/cancel")
    public ResponseEntity<CouponResponse> cancelCoupon(@PathVariable Long couponId) {
        return ResponseEntity.ok(cancelCouponUseCase.cancelCoupon(couponId));
    }


}
