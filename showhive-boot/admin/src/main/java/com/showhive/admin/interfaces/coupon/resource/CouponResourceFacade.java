package com.showhive.admin.interfaces.coupon.resource;

import com.showhive.admin.interfaces.coupon.dto.CreateCouponRequest;
import com.showhive.admin.interfaces.coupon.dto.CouponResponse;
import com.showhive.admin.interfaces.coupon.dto.UseCouponRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Coupon API")
@SecurityRequirement(name = "Authorization")
public interface CouponResourceFacade {

    @Operation(
            summary = "쿠폰 정보 생성",
            requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = CreateCouponRequest.class))),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "쿠폰 생성 성공")
            }
    )
    ResponseEntity<CouponResponse> issueCoupon(CreateCouponRequest request);

    @Operation(
            summary = "쿠폰 정보 조회",
            requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = CouponResponse.class))),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "쿠폰 조회 성공")
            }
    )
    ResponseEntity<CouponResponse> useCoupon(Long couponId, UseCouponRequest request);


    @Operation(
            summary = "모든 쿠폰 정보 조회",
            requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = CouponResponse.class))),
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "쿠폰 취소 성공")
            }
    )
    ResponseEntity<CouponResponse> cancelCoupon(Long couponId);
}
