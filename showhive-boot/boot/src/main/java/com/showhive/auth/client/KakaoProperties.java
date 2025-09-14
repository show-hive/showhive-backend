package com.showhive.auth.client;

import com.showhive.auth.application.dto.AuthDto;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@RequiredArgsConstructor
@ConfigurationProperties(prefix = "oauth.kakao")
public class KakaoProperties {

    private final String clientId;
    private final String redirectUri;

    public MultiValueMap<String, String> createTokenRequestBody(AuthDto authDto) {
        String code = authDto.code();
        //String decodedVerificationCode = URLDecoder.decode(code, StandardCharsets.UTF_8);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "authorization_code");
        map.add("client_id", clientId);
        map.add("redirect_uri", redirectUri);
        map.add("code", code);

        return map;
    }
}
