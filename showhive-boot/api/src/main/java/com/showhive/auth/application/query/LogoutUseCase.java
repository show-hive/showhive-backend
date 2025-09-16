package com.showhive.auth.application.query;

import com.showhive.auth.utils.TokenManager;
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
public class LogoutUseCase {

    private final MemberRepository memberRepository;
    private final TokenManager tokenManager;

    @Transactional
    public void logout(Member member, String refreshToken) {
        long memberId = tokenManager.parseToken(refreshToken);

        if (!member.isSameMember(memberId)) {
            throw new MemberException(MemberErrorCode.UNAUTHORIZED_MEMBER);
        }

        member.deleteRefreshToken();
    }
}

