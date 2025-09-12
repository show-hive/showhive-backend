package com.showhive.auth.api.dto.google;

public record GoogleUserInfo(
        String sub,
        String name,
        String email

        // TODO : picture 가 필요한가?
        // String profileImage;
) {
}
