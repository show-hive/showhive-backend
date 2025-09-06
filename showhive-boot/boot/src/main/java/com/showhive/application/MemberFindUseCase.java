package com.showhive.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.showhive.apis.dto.MemberResponse;
import com.showhive.member.domain.Member;
import com.showhive.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberFindUseCase {

    private final MemberRepository memberRepository;

    public MemberResponse findMember(long memberId) {
        Member member = memberRepository.findById(memberId);
        return MemberResponse.from(member);
    }
}
