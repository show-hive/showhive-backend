package com.showhive.auth.api.dto;

public record SocialLoginRequest(
        String code,
        String state
) {}