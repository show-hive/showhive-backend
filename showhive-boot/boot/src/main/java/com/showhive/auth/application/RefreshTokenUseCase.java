package com.showhive.auth.application;

import com.showhive.auth.api.dto.LoginResponse;
import com.showhive.config.util.JwtTokenProvider;
import com.showhive.member.domain.Member;
import com.showhive.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RefreshTokenUseCase {

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;

    public LoginResponse refresh(String refreshTokenFromCookie) {

        // 유효성 검사 + 사용자 식별
        String username = jwtTokenProvider.getUsernameFromToken(refreshTokenFromCookie);
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("사용자 없음"));

        if (!refreshTokenFromCookie.equals(member.getRefreshToken())) {
            throw new SecurityException("서버에 저장된 리프레시 토큰과 다릅니다.");
        }    // 서버 저장본과 동일한지 확인 (토큰 탈취 방지)

        String newAccessToken = jwtTokenProvider.generateAccessToken(username, member.getRole());
        return new LoginResponse(newAccessToken, null); // 쿠키에서 꺼낸 refreshToken을 추출해서 검증 후 새 AccessToken 발급
    }
}
