package com.showhive.auth.api.dto;

import com.showhive.auth.application.dto.AuthDto;
import jakarta.validation.constraints.NotBlank;

public record AuthRequest(
        @NotBlank
        String code,

        @NotBlank
        String state
) {
    public AuthDto toAuthDto() {
        return new AuthDto(code, state);
    }
}
