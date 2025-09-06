package com.showhive.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import com.showhive.JpaConfig;

@Configuration
@Import(JpaConfig.class)
public class AppConfig {
}
