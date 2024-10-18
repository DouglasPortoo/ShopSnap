package com.douglasporto.ShopSnap.configs;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.http.HttpMethod;

import com.douglasporto.ShopSnap.security.SecurityFilter;

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

  @Autowired
  private SecurityFilter securityFilter;


  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
      http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));
    }

    // .cors(cors -> cors.configurationSource(corsConfigurationSource()))

    return http.csrf(csrf -> csrf.disable())
        .sessionManagement(
            sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(auth ->{ 
          auth.requestMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll();
          auth.requestMatchers(HttpMethod.POST, "/login").permitAll();
          auth.requestMatchers(PUBLIC_MATCHERS).permitAll();
          auth.anyRequest().authenticated();
          })
        .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
  }

  @Bean
  AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
    return configuration.getAuthenticationManager();
  }

  @Bean
  BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  // @Bean
  // CorsConfigurationSource corsConfigurationSource() {
  //   final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
  //   source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
  //   return source;
  // }

}
