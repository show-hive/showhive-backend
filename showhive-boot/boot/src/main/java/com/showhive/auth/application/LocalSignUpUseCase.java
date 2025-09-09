package com.showhive.auth.application;

import com.showhive.auth.application.command.SignUpCommand;
import com.showhive.member.domain.Member;
import com.showhive.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class LocalSignUpUseCase {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    // 예시
    //    public void signUp(MemberSignUpCommand command) {
    //        Member member = Member.create(command.name());
    //        Member member = command.toDomain();
    //        memberRepository.signUp(member);
    //    }

    // 회원가입
    @Transactional
    public void signUp(SignUpCommand command) {

        memberRepository.findByEmailIgnoreCase(command.email())
                .ifPresent(user -> {
                    throw new IllegalStateException("이미 가입된 이메일입니다.");
                });

        String encoded = passwordEncoder.encode(command.password());
        Member member = command.toDomain(encoded);
        memberRepository.signUp(member);
    }

}
