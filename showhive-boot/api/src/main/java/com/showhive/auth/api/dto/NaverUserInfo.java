package com.showhive.auth.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record NaverUserInfo(
        String resultcode,
        String message,
        Response response
) {
    public record Response(
            String id,
            String name,
            String email,
            @JsonProperty("nickname") String nickname,
            // TODO : picture 가 필요한가?
            @JsonProperty("profile_image") String profileImage
    ) {}
}
