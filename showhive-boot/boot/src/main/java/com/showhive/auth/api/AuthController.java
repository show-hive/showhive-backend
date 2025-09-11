package com.showhive.auth.api;

import com.showhive.auth.api.dto.AuthRequest;
import com.showhive.auth.api.dto.LoginResponse;
import com.showhive.auth.application.TokenManager;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.showhive.auth.application.SocialLoginUsecase;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private static final String REFRESH_TOKEN_COOKIE_KEY = "refreshToken";

    private final SocialLoginUsecase socialLoginUseCase;
    private final CookieManager cookieManager;

    @PostMapping("/google")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid AuthRequest request) {

        LoginResponse response = socialLoginUseCase.googleLogin(request.toAuthDto());

        // refresh 쿠키 설정
        ResponseCookie refreshTokenCookie = cookieManager.createCookie(REFRESH_TOKEN_COOKIE_KEY,
                response.refreshToken(),
                TokenManager.REFRESH_TOKEN_EXP);

        return ResponseEntity.status(HttpStatus.CREATED)
                .header(HttpHeaders.AUTHORIZATION, response.accessToken())
                .header(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString())
                .build();
    }


}
