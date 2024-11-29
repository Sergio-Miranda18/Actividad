package com.example.Activdad.Config;

import com.example.Activdad.Services.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // Permitir acceso sin autenticación a /api/auth/login
                        .requestMatchers("/**").permitAll()
                        // Asegurar que los endpoints protegidos solo sean accesibles por ADMIN
                        .anyRequest().hasRole("ADMIN")
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Sin estado (sin sesiones)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class); // Añadir el filtro JWT antes de UsernamePasswordAuthenticationFilter

        return httpSecurity.build();
    }
}
