package com.showhive.auth.api;

import com.showhive.auth.api.dto.AuthRequest;
import com.showhive.auth.api.dto.LoginResponse;
import com.showhive.auth.application.LogoutUsecase;
import com.showhive.auth.application.RefreshUseCase;
import com.showhive.auth.application.TokenManager;
import com.showhive.member.domain.Member;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
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
    private final LogoutUsecase logoutUsecase;
    private final RefreshUseCase refreshUsecase;
    private final CookieManager cookieManager;

    @PostMapping("/google")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid AuthRequest request) {

        LoginResponse newTokens = socialLoginUseCase.googleLogin(request.toAuthDto());
        ResponseCookie refreshTokenCookie = cookieManager.createCookie(REFRESH_TOKEN_COOKIE_KEY,
                newTokens.refreshToken(),
                TokenManager.REFRESH_TOKEN_EXP);

        return ResponseEntity.status(HttpStatus.CREATED)
                .header(HttpHeaders.AUTHORIZATION, newTokens.accessToken())
                .header(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString())
                .build();
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@AuthMember Member member,
                                       @CookieValue(REFRESH_TOKEN_COOKIE_KEY) String refreshToken) {
        logoutUsecase.logout(member, refreshToken);
        ResponseCookie expiredRefreshTokenCookie = cookieManager.deleteCookie(REFRESH_TOKEN_COOKIE_KEY);

        return ResponseEntity.noContent()
                .header(HttpHeaders.SET_COOKIE, expiredRefreshTokenCookie.toString())
                .build();
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponse> refresh(@CookieValue(REFRESH_TOKEN_COOKIE_KEY) String refreshToken) {

        LoginResponse newTokens = refreshUsecase.refresh(refreshToken);
        ResponseCookie refreshTokenCookie = cookieManager.createCookie(REFRESH_TOKEN_COOKIE_KEY,
                newTokens.refreshToken(),
                TokenManager.REFRESH_TOKEN_EXP);

        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.AUTHORIZATION, newTokens.accessToken())
                .header(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString())
                .build();
    }

}
