package com.showhive.auth.api.dto;

import com.showhive.auth.application.dto.AuthDto;
import jakarta.validation.constraints.NotBlank;

public record AuthRequest(
        @NotBlank
        String code,

        // @NotBlank
        String state // naver 제외하고 google, kakao는 state가 null이라 NotBlank 쓰면 안됨
) {
    public AuthDto toAuthDto() {
        return new AuthDto(code, state);
    }
}
