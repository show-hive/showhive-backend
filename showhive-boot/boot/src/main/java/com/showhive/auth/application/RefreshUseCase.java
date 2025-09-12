package com.showhive.auth.application;

import com.showhive.auth.api.dto.LoginResponse;
import com.showhive.member.domain.Member;
import com.showhive.member.exception.MemberErrorCode;
import com.showhive.member.exception.MemberException;
import com.showhive.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RefreshUseCase {

    private final MemberRepository memberRepository;
    private final TokenManager tokenManager;

    @Transactional
    public LoginResponse refresh(String refreshToken) {
        long memberId = tokenManager.parseToken(refreshToken);
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        return issueTokens(member);
    }

    private LoginResponse issueTokens(Member member) {
        String accessToken = tokenManager.createAccessToken(member);
        String refreshToken = tokenManager.createRefreshToken(member);
        member.saveRefreshToken(refreshToken);
        return new LoginResponse(accessToken, refreshToken);
    }
}
