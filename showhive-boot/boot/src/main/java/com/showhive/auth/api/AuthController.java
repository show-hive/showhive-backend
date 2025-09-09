package com.showhive.auth.api;

import com.showhive.auth.api.dto.LoginResponse;
import com.showhive.auth.api.dto.SocialLoginRequest;
import com.showhive.auth.application.SocialLoginUseCase;
import com.showhive.auth.application.LogoutUseCase;
import com.showhive.auth.application.RefreshTokenUseCase;
import com.showhive.auth.application.command.LoginResultCommand;
import com.showhive.auth.application.command.SocialLoginCommand;
import com.showhive.member.domain.SocialInfo.Provider;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final SocialLoginUseCase socialLoginUseCase;
    private final RefreshTokenUseCase refreshTokenUseCase;
    private final LogoutUseCase logoutUseCase;

    // 단일 엔드포인트: /api/auth/{provider}  (google|kakao|naver)
    @PostMapping("/{provider}")
    public ResponseEntity<LoginResponse> login(
            @PathVariable String provider,
            @RequestBody SocialLoginRequest body,
            HttpServletResponse resp) {

        SocialLoginCommand command = new SocialLoginCommand(
                Provider.valueOf(provider.toUpperCase()),
                body.code(),
                body.state()
        );

        LoginResultCommand result = socialLoginUseCase.login(command);

        // refresh 쿠키 설정 (HTTP concern)
        Cookie cookie = new Cookie("refreshToken", result.refreshToken());
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(7 * 24 * 60 * 60);
        resp.addCookie(cookie);

        return ResponseEntity.ok(new LoginResponse(result.accessToken(), null));
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponse> refresh(HttpServletRequest request) {
        String refreshToken = null;
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("refreshToken".equals(cookie.getName())) { refreshToken = cookie.getValue(); }
            }
        }
        LoginResponse response = refreshTokenUseCase.refresh(refreshToken);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader("Authorization") String authorization,
                                       HttpServletResponse response) {
        logoutUseCase.logout(authorization);

        Cookie cookie = new Cookie("refreshToken", null);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);

        return ResponseEntity.ok().build();
    }
}