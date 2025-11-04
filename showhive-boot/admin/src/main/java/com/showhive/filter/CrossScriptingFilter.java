package com.showhive.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CrossScriptingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("XSS Filter 초기화");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        // RequestWrapper로 감싸서 전달
        RequestWrapper wrappedRequest = new RequestWrapper(httpRequest);
        chain.doFilter(wrappedRequest, response);
    }

    @Override
    public void destroy() {
        log.info("XSS Filter destroyed");
    }
}
