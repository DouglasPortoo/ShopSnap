package com.douglasporto.ShopSnap.configs;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.http.HttpMethod;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private static final String[] PUBLIC_MATCHERS = {
    "/h2-console/**",
    "/v2/api-docs",
    "/swagger-resources/**",
    "/swagger-ui.html",
    "/webjars/**"
  };

  private static final String[] PUBLIC_MATCHERS_GET = {
    "/produtos/**",
    "/categorias/**"
  };


  @Autowired
  private Environment env;

  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
      http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));
    }

    http.csrf(csrf -> csrf.disable());
    http
        .authorizeHttpRequests((authz) -> authz
            .requestMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
            .requestMatchers(PUBLIC_MATCHERS).permitAll()
            .anyRequest().authenticated())
        .httpBasic(Customizer.withDefaults());
    http.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    return http.build();
  }


}
