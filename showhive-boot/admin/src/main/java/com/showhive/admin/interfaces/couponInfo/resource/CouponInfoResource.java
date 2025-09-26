package com.showhive.admin.interfaces.couponInfo.resource;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.showhive.admin.application.command.dto.couponInfo.CouponInfoDto;
import com.showhive.admin.application.command.usecase.couponInfo.CreateCouponInfoUseCase;
import com.showhive.admin.application.command.usecase.couponInfo.GetCouponInfoUseCase;
import com.showhive.admin.interfaces.couponInfo.dto.CouponInfoReqeust;
import com.showhive.admin.interfaces.couponInfo.dto.CouponInfoResponse;
import com.showhive.auth.RequireRole;
import com.showhive.member.domain.Role;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("couponControllerV2")
@RequiredArgsConstructor
@RequireRole(role = Role.MANAGER)
@RequestMapping("/admin/v2/coupon-infos")
public class CouponInfoResource {

    private final CreateCouponInfoUseCase createCouponInfoUseCase;
    private final GetCouponInfoUseCase getCouponInfoUseCase;

    @PostMapping
    public ResponseEntity<CouponInfoResponse> createCouponInfo(@RequestBody CouponInfoReqeust request)
            throws JsonProcessingException {
        CouponInfoDto commandDto = CouponInfoDto.of(request);
        createCouponInfoUseCase.create(commandDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CouponInfoResponse> getCouponInfo(@PathVariable Long id) {
        CouponInfoResponse response = CouponInfoResponse.from(getCouponInfoUseCase.get(id));
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<CouponInfoResponse>> getAllCouponInfo() {
        return ResponseEntity.ok(getCouponInfoUseCase.getAll().stream()
                .map(CouponInfoResponse::from)
                .collect(Collectors.toList()));
    }
}
