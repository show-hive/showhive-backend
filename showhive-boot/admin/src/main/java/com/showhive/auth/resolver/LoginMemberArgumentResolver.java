package com.showhive.auth.resolver;

import com.showhive.auth.AuthMember;
import com.showhive.auth.TokenParser;
import com.showhive.auth.usecase.MemberFindUseCase;
import com.showhive.member.domain.Member;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * Controller 메서드에서 @AuthMember 애노테이션이 붙은 파라미터를 해석해,
 * 현재 로그인한 사용자의 Member 객체를 주입해주는 ArgumentResolver.
 * <p>
 * 즉, 컨트롤러에서는 토큰 파싱이나 멤버 조회 로직을 몰라도
 *
 * @AuthMember Member member 파라미터만 선언하면 로그인 멤버를 바로 사용할 수 있다.
 */
@Component
@RequiredArgsConstructor
public class LoginMemberArgumentResolver implements HandlerMethodArgumentResolver {

    private final MemberFindUseCase memberFindUseCase;
    private final TokenParser tokenParser;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AuthMember.class);
    }

    @Override
    public Member resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        Member member = (Member) request.getAttribute("loginMember");
        if (member != null) {
            return member;
        }
        String accessToken = webRequest.getHeader(HttpHeaders.AUTHORIZATION);
        long memberId = tokenParser.parseToken(accessToken);
        return memberFindUseCase.findLoginMember(memberId);
    }
}
