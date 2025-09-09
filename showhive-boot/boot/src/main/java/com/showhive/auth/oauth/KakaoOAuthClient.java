package com.showhive.auth.oauth;

import com.showhive.member.domain.SocialInfo.Provider;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


@Component
@RequiredArgsConstructor
public class KakaoOAuthClient {
    private final RestTemplate rest;

    @Value("${kakao.client-id}")
    String clientId;
    @Value("${kakao.redirect-uri}")
    String redirectUri;


    // 인가 코드로 액세스 토큰 교환
    public String exchangeCode(String code) {
        String tokenUrl = "https://kauth.kakao.com/oauth/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", clientId);
        params.add("redirect_uri", redirectUri);
        params.add("code", code);

        HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<>(params, headers);
        Map<String, Object> body = rest.postForObject(tokenUrl, req, Map.class);

        if (body == null || body.get("access_token") == null) {
            throw new IllegalStateException("Kakao access token 응답 없음");
        }
        return (String) body.get("access_token");
    }

    // 액세스 토큰으로 사용자 정보 조회
    public SocialProfileDto fetchProfile(String accessToken) {
        String url = "https://kapi.kakao.com/v2/user/me";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<Void> req = new HttpEntity<>(headers);
        ResponseEntity<Map> res = rest.exchange(url, HttpMethod.GET, req, Map.class);
        Map<String, Object> body = res.getBody();

        if (body == null) {
            throw new IllegalStateException("Kakao 사용자 정보 조회 실패");
        }

        String id = String.valueOf(body.get("id"));
        Map<String, Object> kakaoAccount = (Map<String, Object>) body.get("kakao_account");

        String email = null;
        String name = null;

        if (kakaoAccount != null) {
            email = (String) kakaoAccount.get("email");
            Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
            if (profile != null) {
                name = (String) profile.get("nickname");
            }
            if (name == null) {
                name = (String) kakaoAccount.get("name");
            }
        }

        return new SocialProfileDto(Provider.KAKAO, id, email, name);
    }
}