package com.jp.e_m_s.Security;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

/*this is demo project

yes
yes
yes*/
@Configuration
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers("/auth/**").permitAll()

                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        // EMPLOYEE + ADMIN can view
                        .requestMatchers(HttpMethod.GET, "/employees/**")
                        .authenticated()

                        .requestMatchers(HttpMethod.GET, "/departments/**")
                        .authenticated()

                        // Only ADMIN can modify employees
                        .requestMatchers(HttpMethod.POST, "/employees/**")
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.PUT, "/employees/**")
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.DELETE, "/employees/**")
                        .hasRole("ADMIN")

                        // Only ADMIN can modify departments
                        .requestMatchers(HttpMethod.POST, "/departments/**")
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.PUT, "/departments/**")
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.DELETE, "/departments/**")
                        .hasRole("ADMIN")

                        // Leave approval/rejection → ADMIN
                        .requestMatchers(HttpMethod.PUT, "/leaves/*/approve")
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.PUT, "/leaves/*/reject")
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/leaves/**")
                        .hasRole("ADMIN")

                        .anyRequest().authenticated()
                );
        http.addFilterBefore(
                jwtAuthenticationFilter,
                UsernamePasswordAuthenticationFilter.class
                 );

        return http.build();
    }


        @Bean
        public CorsConfigurationSource corsConfigurationSource() {

            CorsConfiguration configuration = new CorsConfiguration();

            configuration.setAllowedOrigins(
                    List.of("http://localhost:5173")
            );

            configuration.setAllowedMethods(
                    List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")
            );

            configuration.setAllowedHeaders(
                    List.of("*")
            );

            configuration.setAllowCredentials(true);

            UrlBasedCorsConfigurationSource source =
                    new UrlBasedCorsConfigurationSource();

            source.registerCorsConfiguration("/**", configuration);

            return source;
        }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {

        return configuration.getAuthenticationManager();
    }


}
