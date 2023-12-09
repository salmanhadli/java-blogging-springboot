package com.project.blogging.security;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig {

  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    // http
    //   .authorizeHttpRequests(authz -> authz.anyRequest().authenticated())
    //   .httpBasic(withDefaults());
    // http.cors().disable().csrf().disable();
    // http
    //   .csrf()
    //   .disable()
    //   .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
    //   .httpBasic(withDefaults());
    http.authorizeHttpRequests().requestMatchers("/**").permitAll();
    return http.build();
  }
}
