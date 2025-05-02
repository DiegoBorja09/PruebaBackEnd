package com.test.diego.config;

import com.test.diego.service.impl.CustomUserDetailsService;
import com.test.diego.util.JwtTokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider tokenProvider;
    private final CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain)
            throws java.io.IOException, jakarta.servlet.ServletException {
        String header = req.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            if (tokenProvider.validateToken(token)) {
                String username = tokenProvider.getUsername(token);
                var userDetails = userDetailsService.loadUserByUsername(username);
                var auth = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities()
                );
                auth.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(req)
                );
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        chain.doFilter(req, res);
    }
}

