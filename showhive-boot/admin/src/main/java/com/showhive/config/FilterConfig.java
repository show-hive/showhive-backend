package com.showhive.config;

import com.showhive.filter.CrossScriptingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<CrossScriptingFilter> xssFilterRegistration() {
        FilterRegistrationBean<CrossScriptingFilter> registrationBean =
                new FilterRegistrationBean<>();

        registrationBean.setFilter(new CrossScriptingFilter());
        registrationBean.addUrlPatterns("/api/*", "/admin/*");
        registrationBean.setOrder(1);
        registrationBean.setName("CrossScriptingFilter");

        return registrationBean;
    }
}
