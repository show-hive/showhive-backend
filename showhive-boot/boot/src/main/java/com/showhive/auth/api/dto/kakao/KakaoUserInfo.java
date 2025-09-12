package com.showhive.auth.api.dto.kakao;

public record KakaoUserInfo(
        String id,
        String nickname,
        String email

        // TODO : picture 가 필요한가?
        // String profileImage;
) {
}
