package com.showhive.auth.application.dto;

public record AuthDto(
        String code,
        String state
) {
}
