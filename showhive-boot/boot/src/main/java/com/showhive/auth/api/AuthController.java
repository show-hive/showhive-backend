package com.showhive.auth.api;

import com.showhive.auth.api.dto.AuthRequest;
import com.showhive.auth.api.dto.LoginResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

    private final SocialLoginUsecase socialLoginUseCase;

    @PostMapping("/google")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid AuthRequest request) {

        LoginResponse response = socialLoginUseCase.googleLogin(request.toAuthDto());

        // refresh 쿠키 설정 (HTTP concern)
//        Cookie cookie = new Cookie("refreshToken", result.refreshToken());
//        cookie.setHttpOnly(true);
//        cookie.setPath("/");
//        cookie.setMaxAge(7 * 24 * 60 * 60);
//        resp.addCookie(cookie);

        return ResponseEntity.ok(response);
    }


}
