package com.test.diego.config;

import com.test.diego.service.impl.CustomUserDetailsService;
import com.test.diego.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;

import java.beans.Customizer;
import java.util.List;

import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final CustomUserDetailsService userDetailsService;
  private final JwtTokenProvider tokenProvider;

  @Bean
  AuthenticationManager authManager(AuthenticationConfiguration cfg) throws Exception {
    return cfg.getAuthenticationManager();
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowedOrigins(List.of("http://localhost:4200"));
    config.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTIONS"));
    config.setAllowedHeaders(List.of("*"));
    config.setAllowCredentials(true);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", config);
    return source;
  }

  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    JwtAuthenticationFilter jwtFilter = new JwtAuthenticationFilter(tokenProvider, userDetailsService);

    http
    .cors(cors -> {})

      .csrf(csrf -> csrf.disable())

      .sessionManagement(sm -> sm
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))


      .authorizeHttpRequests(auth -> auth
        .requestMatchers("/auth/**").permitAll()
        .requestMatchers(HttpMethod.PUT, "/api/loans/*/status").hasRole("ADMIN")
        .requestMatchers("/api/loans/**").hasAnyRole("USER","ADMIN")
        .anyRequest().authenticated()
      )

      .userDetailsService(userDetailsService)

      .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }
}
