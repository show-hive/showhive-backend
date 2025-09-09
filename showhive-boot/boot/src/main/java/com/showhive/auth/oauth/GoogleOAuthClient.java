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
public class GoogleOAuthClient {

    private final RestTemplate rest;

    @Value("${google.client-id}")
    String clientId;
    @Value("${google.client-secret}")
    String clientSecret;
    @Value("${google.redirect-uri}")
    String redirectUri;

    // 인가 코드로 액세스 토큰 교환
    public String exchangeCode(String code) {
        String tokenUrl = "https://oauth2.googleapis.com/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("code", code);
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("redirect_uri", redirectUri);
        params.add("grant_type", "authorization_code");

        HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<>(params, headers);
        Map<String, Object> body = rest.postForObject(tokenUrl, req, Map.class);

        if (body == null || body.get("access_token") == null) {
            throw new IllegalStateException("Google access token 응답 없음");
        }
        return (String) body.get("access_token");
    }

    //  액세스 토큰으로 사용자 정보 조회
    public SocialProfileDto fetchProfile(String accessToken) {
        String url = "https://www.googleapis.com/oauth2/v2/userinfo";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<Void> req = new HttpEntity<>(headers);
        ResponseEntity<Map> res = rest.exchange(url, HttpMethod.GET, req, Map.class);
        Map userInfo = res.getBody();

        if (userInfo == null) {
            throw new IllegalStateException("Google 사용자 정보 조회 실패");
        }

        String id = String.valueOf(userInfo.get("id"));
        String email = (String) userInfo.get("email");
        String name = (String) userInfo.get("name");

        if (name == null) {
            String given = (String) userInfo.get("given_name");
            String family = (String) userInfo.get("family_name");
            name = (given != null ? given : "") + (family != null ? " " + family : "");
        }

        return new SocialProfileDto(Provider.GOOGLE, id, email, name);
    }
}