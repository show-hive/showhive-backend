package com.showhive.auth.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public record KakaoUserInfo(
        String id,
        @JsonProperty("kakao_account") KakaoAccount kakao_account,
        Properties properties // 예전 필드 호환(있으면 닉네임이 여기에도 있을 수 있음)

        // TODO : picture 가 필요한가?
        // String profileImage;
) {
    public String nickname() {
        if (kakao_account != null && kakao_account.profile() != null
                && kakao_account.profile().nickname() != null) {
            return kakao_account.profile().nickname();
        }
        return properties != null ? properties.nickname() : null;
    }

    public String email() {
        if (kakao_account != null && Boolean.TRUE.equals(kakao_account.has_email())) {
            return kakao_account.email();
        }
        return null;
    }

    public record KakaoAccount(Boolean profile_needs_agreement,
                               Profile profile,
                               Boolean has_email,
                               String email) {
    }

    public record Profile(String nickname,
                          String profile_image_url,
                          String thumbnail_image_url) {
    }

    public record Properties(String nickname) {
    }

}
