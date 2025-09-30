package com.showhive.admin.interfaces.couponInfo.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.showhive.admin.interfaces.couponInfo.dto.CreateCouponInfoRequest;
import com.showhive.admin.interfaces.couponInfo.dto.CouponInfoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import java.util.List;
import org.springframework.http.ResponseEntity;

@Tag(name = "CouponInfo API")
@SecurityRequirement(name = "Authorization")
public interface CouponInfoFacade {

    @Operation(
            summary = "쿠폰 정보 생성",
            requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = CreateCouponInfoRequest.class))),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "쿠폰 정보 생성 성공")
            }
    )
    ResponseEntity<CouponInfoResponse> createCouponInfo(CreateCouponInfoRequest request) throws JsonProcessingException;

    @Operation(
            summary = "쿠폰 정보 조회",
            requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = CouponInfoResponse.class))),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "쿠폰 정보 조회 성공")
            }
    )
    ResponseEntity<CouponInfoResponse> getCouponInfo(Long id);


    @Operation(
            summary = "모든 쿠폰 정보 조회",
            requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = CouponInfoResponse.class))),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "모든 쿠폰 정보 조회 성공")
            }
    )
    ResponseEntity<List<CouponInfoResponse>> getAllCouponInfo();


}
