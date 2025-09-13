package com.showhive.member.application.query;

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
public class MemberFindUseCase {

    private final MemberRepository memberRepository;
    private final TokenManager tokenManager;
    
    public Member findLoginMember(String accessToken) {
        long memberId = tokenManager.parseToken(accessToken);
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
    }
}
