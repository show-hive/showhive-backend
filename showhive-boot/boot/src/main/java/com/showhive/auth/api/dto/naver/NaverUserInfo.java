package com.showhive.auth.api.dto.naver;

public record NaverUserInfo(
        String id,
        String name,
        String email

        // TODO : picture 가 필요한가?
        // String profileImage;
) {
}
