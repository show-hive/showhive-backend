package com.showhive.auth.oauth;


import com.showhive.member.domain.SocialInfo.Provider;

public record SocialProfileDto(
        Provider provider,
        String providerId,
        String email,
        String name
) {
}
