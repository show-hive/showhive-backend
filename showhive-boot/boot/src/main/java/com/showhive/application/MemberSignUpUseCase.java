package com.showhive.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.showhive.application.command.MemberSignUpCommand;
import com.showhive.member.domain.Member;
import com.showhive.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberSignUpUseCase {

    private final MemberRepository memberRepository;
    
    public void signUp(MemberSignUpCommand command) {
        Member member = Member.create(command.name());
//        Member member = command.toDomain();
        memberRepository.signUp(member);
    }
}
