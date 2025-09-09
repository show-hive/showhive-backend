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
public class NaverOAuthClient {
    private final RestTemplate rest;
    @Value("${naver.client-id}")
    String clientId;
    @Value("${naver.client-secret}")
    String clientSecret;
    @Value("${naver.redirect-uri}")
    String redirectUri;

    // 인가 코드로 액세스 토큰 교환
    public String exchangeCode(String code, String state) {
        String tokenUrl = "https://nid.naver.com/oauth2.0/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("code", code);
        params.add("state", state);

        HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<>(params, headers);
        Map<String, Object> body = rest.postForObject(tokenUrl, req, Map.class);

        if (body == null || body.get("access_token") == null) {
            throw new IllegalStateException("Naver access token 응답 없음");
        }
        return (String) body.get("access_token");
    }

    // 액세스 토큰으로 사용자 정보 조회
    public SocialProfileDto fetchProfile(String accessToken) {
        String url = "https://openapi.naver.com/v1/nid/me";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<Void> req = new HttpEntity<>(headers);
        ResponseEntity<Map> res = rest.exchange(url, HttpMethod.GET, req, Map.class);
        Map<String, Object> body = res.getBody();

        if (body == null) {
            throw new IllegalStateException("Naver 사용자 정보 조회 실패");
        }

        Map<String, Object> response = (Map<String, Object>) body.get("response");
        if (response == null) {
            throw new IllegalStateException("Naver 사용자 정보 구조 오류");
        }

        String id = (String) response.get("id");
        String email = (String) response.get("email");
        String name = (String) response.get("name");

        return new SocialProfileDto(Provider.NAVER, id, email, name);
    }
}