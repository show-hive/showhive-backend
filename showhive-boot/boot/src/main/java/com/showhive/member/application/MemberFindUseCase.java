package com.showhive.member.application;

import com.showhive.member.api.dto.MemberResponse;
import com.showhive.member.domain.Member;
import com.showhive.member.exception.MemberErrorCode;
import com.showhive.member.exception.MemberException;
import com.showhive.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberFindUseCase {

    private final MemberRepository memberRepository;

    public MemberResponse findMember(long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        return MemberResponse.from(member);
    }
}
