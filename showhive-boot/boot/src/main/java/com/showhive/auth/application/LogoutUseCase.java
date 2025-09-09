package com.showhive.auth.application;

import com.showhive.config.util.JwtTokenProvider;
import com.showhive.member.domain.Member;
import com.showhive.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class LogoutUseCase {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public void logout(String accessToken) {
        String token = accessToken.replace("Bearer ", "");
        String username = jwtTokenProvider.getUsernameFromToken(token);

        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("사용자 없음"));

        member.deleteRefreshToken(); // 서버에 저장된 refreshToken 제거

    }
}