package com.showhive.auth.application;

import com.showhive.auth.api.dto.LoginRequest;
import com.showhive.auth.api.dto.LoginResponse;
import com.showhive.config.util.JwtTokenProvider;
import com.showhive.member.domain.Member;
import com.showhive.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class LocalLoginUseCase {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    // 자체 로그인
    @Transactional
    public LoginResponse login(LoginRequest request) {
        Member member = memberRepository
                .findByEmailIgnoreCaseAndPasswordIsNotNull(request.email())
                .orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 이메일입니다."));

        if (member.getPassword() == null) {
            throw new IllegalStateException("소셜 계정은 비밀번호로 로그인할 수 없습니다.");
        }
        if (!passwordEncoder.matches(request.password(), member.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        String accessToken = jwtTokenProvider.generateAccessToken(member.getUsername(), member.getRole());
        String refreshToken = jwtTokenProvider.generateRefreshToken(member.getUsername());

        member.makeRefreshToken(refreshToken);
        memberRepository.login(member);

        return new LoginResponse(accessToken, refreshToken);
    }
}
