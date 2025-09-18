package com.showhive.auth.interceptor;

import com.showhive.auth.RequireRole;
import com.showhive.auth.usecase.MemberFindUseCase;
import com.showhive.member.domain.Member;
import com.showhive.member.domain.Role;
import com.showhive.member.exception.MemberErrorCode;
import com.showhive.member.exception.MemberException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class AdminInterceptor implements HandlerInterceptor {

    private final MemberFindUseCase memberFindUseCase;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod handlerMethod) {
            checkRequiredRole(handlerMethod, request);
        }
        return true;
    }

    private void checkRequiredRole(HandlerMethod handlerMethod, HttpServletRequest request) {
        RequireRole classAnnotation = handlerMethod.getBeanType().getAnnotation(RequireRole.class);
        if (classAnnotation != null) {
            Member member = extractMember(request);
            validateUserRole(member, classAnnotation.role());
        }

        RequireRole methodAnnotation = handlerMethod.getMethodAnnotation(RequireRole.class);
        if (methodAnnotation != null) {
            Member member = extractMember(request);
            validateUserRole(member, methodAnnotation.role());
        }
    }

    private Member extractMember(HttpServletRequest request) {
        String accessToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        Member member = memberFindUseCase.findLoginMember(accessToken);
        request.setAttribute("loginMember", member);
        return member;
    }

    private void validateUserRole(Member member, Role requiredRole) {
        if (!member.canAccess(requiredRole)) {
            throw new MemberException(MemberErrorCode.FORBIDDEN_MEMBER);
        }
    }
}
