package com.showhive.auth.oauth;

import com.showhive.member.domain.SocialInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OAuthGateway {

    private final GoogleOAuthClient google;
    private final KakaoOAuthClient kakao;
    private final NaverOAuthClient naver;

    //  인가 코드 → 액세스 토큰
    public String exchangeCodeForAccessToken(SocialInfo.Provider p, String code, String stateOrNull) {
        return switch (p) {
            case GOOGLE -> google.exchangeCode(code);
            case KAKAO  -> kakao.exchangeCode(code);
            case NAVER  -> naver.exchangeCode(code, stateOrNull);
        };
    }

    // 액세스 토큰 → 외부 사용자 정보
    public SocialProfileDto fetchProfile(SocialInfo.Provider p, String accessToken) {
        return switch (p) {
            case GOOGLE -> google.fetchProfile(accessToken);
            case KAKAO  -> kakao.fetchProfile(accessToken);
            case NAVER  -> naver.fetchProfile(accessToken);
        };
    }
}

// 유스케이스는 외부 호출 세부사항을 몰라야 테스트가 쉬워짐.
// 인터페이스 없이 구현 빈 하나(@Component)로 라우팅까지 처리해도 OK.