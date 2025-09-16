package com.showhive.auth.api.dto;

public record LoginResponse(
        String accessToken,
        String refreshToken
) {
}
