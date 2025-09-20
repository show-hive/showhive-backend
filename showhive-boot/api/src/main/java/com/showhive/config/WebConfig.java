package com.showhive.config;

import com.showhive.auth.resolver.LoginMemberArgumentResolver;
import com.showhive.auth.utils.TokenManager;
import com.showhive.member.application.query.MemberFindUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final MemberFindUseCase memberFindUseCase;
    private final TokenManager tokenManager;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new LoginMemberArgumentResolver(memberFindUseCase, tokenManager));
    }
}
