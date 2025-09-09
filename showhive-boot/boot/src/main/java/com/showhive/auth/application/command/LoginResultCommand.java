package com.showhive.auth.application.command;

public record LoginResultCommand(
        Long userId,
        String username,
        String accessToken,
        String refreshToken
) {
}
