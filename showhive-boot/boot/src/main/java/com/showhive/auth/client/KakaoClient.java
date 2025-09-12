package com.showhive.auth.client;

import com.showhive.ShowHiveException;
import com.showhive.auth.api.dto.kakao.KakaoTokenResponse;
import com.showhive.auth.api.dto.kakao.KakaoUserInfo;
import com.showhive.auth.application.dto.AuthDto;
import com.showhive.exception.ErrorCode;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
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

    public KakaoTokenResponse requestToken(AuthDto authDto) {
        try {
            return restClient.post()
                    .uri("https://kauth.kakao.com/oauth/token")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(kakaoProperties.createTokenRequestBody(authDto))
                    .retrieve()
                    .body(KakaoTokenResponse.class);
        } catch (RestClientException exception) {
            throw new ShowHiveException(ErrorCode.INTERNAL_SERVER_ERROR.getMessage(), ErrorCode.INTERNAL_SERVER_ERROR.getStatusCode());
        }
    }

    public KakaoUserInfo requestUserInfo(KakaoTokenResponse response) {
        return restClient.get()
                .uri("https://kapi.kakao.com/v2/user/me")
                .headers(headers -> headers.setBearerAuth(response.accessToken()))
                .retrieve()
                .body(KakaoUserInfo.class);
    }
}
