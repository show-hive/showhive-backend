package com.showhive.auth.application;

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

        Member loginMember = memberRepository.findById(member.getId())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        if (!loginMember.isSameMember(memberId)) {
            throw new MemberException(MemberErrorCode.UNAUTHORIZED_MEMBER);
        }

        member.deleteRefreshToken();
    }
}

