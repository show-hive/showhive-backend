package com.showhive.auth.client;

import com.showhive.ShowHiveException;
import com.showhive.auth.api.dto.GoogleTokenResponse;
import com.showhive.auth.api.dto.GoogleUserInfo;
import com.showhive.auth.application.dto.AuthDto;
import com.showhive.exception.ErrorCode;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Component
@EnableConfigurationProperties(GoogleProperties.class)
public class GoogleClient {

    private final RestClient restClient;
    private final GoogleProperties googleProperties;

    public GoogleClient(GoogleProperties googleProperties) {
        this.restClient = RestClient.create();
        this.googleProperties = googleProperties;
    }

    public GoogleTokenResponse requestToken(AuthDto authDto) {
        try {
            return restClient.post()
                    .uri("https://oauth2.googleapis.com/token")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(googleProperties.createTokenRequestBody(authDto))
                    .retrieve()
                    .body(GoogleTokenResponse.class);
        } catch (RestClientException exception) {
            throw new ShowHiveException(ErrorCode.INTERNAL_SERVER_ERROR.getMessage(), ErrorCode.INTERNAL_SERVER_ERROR.getStatusCode());
        }
    }

    public GoogleUserInfo requestUserInfo(GoogleTokenResponse response) {
        return restClient.get()
                .uri("https://www.googleapis.com/oauth2/v3/userinfo")
                .headers(headers -> headers.setBearerAuth(response.accessToken()))
                .retrieve()
                .body(GoogleUserInfo.class);
    }
}
