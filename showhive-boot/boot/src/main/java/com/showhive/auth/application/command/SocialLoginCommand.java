package com.showhive.auth.application.command;

import com.showhive.member.domain.SocialInfo.Provider;

// 요청용 커맨드
public record SocialLoginCommand(
        Provider provider,
        String code,
        String stateOrNull

) {

}