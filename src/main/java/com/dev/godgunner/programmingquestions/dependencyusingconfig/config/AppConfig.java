package com.dev.godgunner.programmingquestions.dependencyusingconfig.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan("com.dev.godgunner.programmingquestions.dependencyusingconfig")
public class AppConfig {
}
