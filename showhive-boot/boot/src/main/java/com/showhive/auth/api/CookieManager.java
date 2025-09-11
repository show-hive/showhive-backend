package com.showhive.auth.api;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.server.Cookie.SameSite;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CookieManager {

    private static final String PATH = "/";
    private static final String SAME_SITE = SameSite.NONE.attributeValue();

    public ResponseCookie createCookie(String key, String value, long maxAge) {
        return ResponseCookie.from(key, value)
                .maxAge(maxAge)
                .path(PATH)
                .sameSite(SAME_SITE)
                .secure(false)
                .build();
    }

    public ResponseCookie deleteCookie(String key) {
        return ResponseCookie.from(key, "")
                .maxAge(0)
                .path(PATH)
                .sameSite(SAME_SITE)
                .secure(false)
                .build();
    }
}
