package com.showhive.auth.client;

import com.showhive.ShowHiveException;
import com.showhive.auth.api.dto.KakaoUserInfo;
import com.showhive.auth.api.dto.TokenResponse;
import com.showhive.auth.application.dto.AuthDto;
import com.showhive.exception.ErrorCode;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Component
@EnableConfigurationProperties(KakaoProperties.class)
public class KakaoClient {

    private final RestClient restClient;
    private final KakaoProperties kakaoProperties;

    public KakaoClient(KakaoProperties kakaoProperties) {
        this.restClient = RestClient.create();
        this.kakaoProperties = kakaoProperties;
    }

    public TokenResponse requestToken(AuthDto authDto) {
        try {
            return restClient.post()
                    .uri("https://kauth.kakao.com/oauth/token")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(kakaoProperties.createTokenRequestBody(authDto))
                    .retrieve()
                    .body(TokenResponse.class);
        } catch (RestClientException exception) {
            throw new ShowHiveException(ErrorCode.INTERNAL_SERVER_ERROR.getMessage(),
                    ErrorCode.INTERNAL_SERVER_ERROR.getStatusCode());
        }
    }

    public KakaoUserInfo requestUserInfo(TokenResponse response) {
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("property_keys",
                "[\"kakao_account.profile.nickname\",\"kakao_account.has_email\",\"kakao_account.email\",\"properties.nickname\"]");

        return restClient.post()
                .uri("https://kapi.kakao.com/v2/user/me")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .headers(h -> h.setBearerAuth(response.accessToken()))
                .body(form)
                .retrieve()
                .body(KakaoUserInfo.class);
    }
}

