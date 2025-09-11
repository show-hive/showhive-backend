package com.showhive.auth.application;

import com.showhive.auth.api.dto.GoogleTokenResponse;
import com.showhive.auth.api.dto.GoogleUserInfo;
import com.showhive.auth.api.dto.LoginResponse;
import com.showhive.auth.application.dto.AuthDto;
import com.showhive.auth.client.GoogleClient;
import com.showhive.member.domain.Member;
import com.showhive.member.domain.ProviderType;
import com.showhive.member.domain.SocialInfo;
import com.showhive.member.exception.MemberErrorCode;
import com.showhive.member.exception.MemberException;
import com.showhive.member.repository.MemberRepository;
import com.showhive.member.repository.SocialInfoRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LogoutUsecase {

    private final MemberRepository memberRepository;
    private final TokenManager tokenManager;

    @Transactional
    public void logout(Member member, String refreshToken) {
        long memberId = tokenManager.parseToken(refreshToken);

        Member loginMember = memberRepository.findById(member.getId())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        if (!loginMember.isSameMember(memberId)) {
            throw new MemberException(MemberErrorCode.UNAUTHORIZED_MEMBER);
        }

        member.deleteRefreshToken();
    }
}

