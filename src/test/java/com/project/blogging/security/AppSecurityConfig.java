package com.project.blogging.security;

import static org.springframework.security.config.Customizer.withDefaults;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityConfigurer;
import org.springframework.security.web.SecurityConfigurerAdapter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig {

  // @Override
  // protected void configure(HttpSecurity http) {
  //   http
  //     .authorizeRequests()
  //     .anyRequest()
  //     .permitAll()
  //     .and()
  //     .csrf()
  //     .disable()
  //     .cors()
  //     .disable();
  //   return http.build();
  // }

  @Bean
  public SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> securityConfigurer() {
    return new SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {
      @Override
      public void configure(HttpSecurity http) throws Exception {
        http
          .authorizeRequests(requests -> requests.anyRequest().permitAll())
          .cors(cors -> cors.disable())
          .csrf(csrf -> csrf.disable())
          .build();
      }
    };
  }
  // @Bean
  // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
  //   http
  //     .cors(cors -> cors.disable())
  //     .csrf(csrf -> csrf.disable())
  //     .authorizeHttpRequests(requests -> requests.anyRequest().permitAll());
  //   return http.build();
  // }
}
