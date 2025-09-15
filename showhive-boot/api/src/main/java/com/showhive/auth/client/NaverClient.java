package com.showhive.auth.client;

import com.showhive.ShowHiveException;
import com.showhive.auth.api.dto.NaverUserInfo;
import com.showhive.auth.api.dto.TokenResponse;
import com.showhive.auth.application.dto.AuthDto;
import com.showhive.exception.ErrorCode;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Component
@EnableConfigurationProperties(NaverProperties.class)
public class NaverClient {

    private final RestClient restClient;
    private final NaverProperties naverProperties;

    public NaverClient(NaverProperties naverProperties) {
        this.restClient = RestClient.create();
        this.naverProperties = naverProperties;
    }

    public TokenResponse requestToken(AuthDto authDto) {
        try {
            return restClient.post()
                    .uri("https://nid.naver.com/oauth2.0/token")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(naverProperties.createTokenRequestBody(authDto))
                    .retrieve()
                    .body(TokenResponse.class);
        } catch (RestClientException exception) {
            throw new ShowHiveException(ErrorCode.INTERNAL_SERVER_ERROR.getMessage(), ErrorCode.INTERNAL_SERVER_ERROR.getStatusCode());
        }
    }

    public NaverUserInfo.Response requestUserInfo(TokenResponse response) {
        return restClient.get()
                .uri("https://openapi.naver.com/v1/nid/me")
                .headers(headers -> headers.setBearerAuth(response.accessToken()))
                .retrieve()
                .body(NaverUserInfo.class)
                .response();
    }
}
