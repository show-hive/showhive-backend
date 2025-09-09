package com.showhive.auth.application.command;

import com.showhive.member.domain.Member;
import com.showhive.member.domain.SocialInfo.Provider;

public record SocialUpsertCommand(
        Provider provider,
        String providerUserId,
        String email,
        String name
) {
    public Member toDomain() {
        return Member.signUpSocial(provider, providerUserId, email, name);
    }
}
