package com.jp.e_m_s.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final EmployeeUserDetailsService employeeUserDetailsService;

    public JwtAuthenticationFilter(
            JwtService jwtService,
            EmployeeUserDetailsService employeeUserDetailsService) {

        this.jwtService = jwtService;
        this.employeeUserDetailsService = employeeUserDetailsService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        // 1. Get Authorization header
        String authHeader = request.getHeader("Authorization");

        String token = null;
        String username = null;

        // 2. Check Bearer token
        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            // 3. Extract token
            token = authHeader.substring(7);

            // 4. Extract username/email from JWT
            username = jwtService.extractUsername(token);
        }

        // 5. Check whether user is already authenticated
        if (username != null &&
                SecurityContextHolder.getContext().getAuthentication() == null) {

            // 6. Load employee from database
            EmployeeUserDetails userDetails =
                    (EmployeeUserDetails)
                            employeeUserDetailsService
                                    .loadUserByUsername(username);

            // 7. Validate JWT
            if (jwtService.isTokenValid(token, userDetails)) {

                // 8. Create Authentication object
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                // 9. Put authenticated user into SecurityContext
                SecurityContextHolder.getContext()
                        .setAuthentication(authentication);

            }
        }

        // 10. Continue request
        filterChain.doFilter(request, response);
    }
}