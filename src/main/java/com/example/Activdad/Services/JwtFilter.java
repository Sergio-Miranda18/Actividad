package com.example.Activdad.Services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;
import java.util.Collections;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private static final String SECRET_KEY = "my-secret-key-which-is-very-secure"; // Asegúrate de usar una clave segura

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        // Extraer el token de la cabecera Authorization
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7); // Obtener solo el token sin "Bearer "

            try {
                // Validar el token y obtener las claims
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(SECRET_KEY.getBytes()) // Decodificación con la clave secreta
                        .build()
                        .parseClaimsJws(token)
                        .getBody();

                // Obtener el nombre de usuario y el rol
                String username = claims.getSubject();
                String role = claims.get("role", String.class); // Obtener el rol

                System.out.println("Token decodificado correctamente: ");
                System.out.println("Username: " + username);
                System.out.println("Role: " + role);

                if (username != null && role != null) {
                    // Verificar que el rol sea ADMIN
                    if (role.equals("ADMIN")) {
                        SecurityContextHolder.getContext().setAuthentication(
                                new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList())
                        );
                    } else {
                        // Si el rol no es ADMIN, rechazar con 403
                        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Acceso denegado");
                        return;
                    }
                }
            } catch (Exception e) {
                // Si el token no es válido, responder con 401
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido");
                return;
            }
        }

        // Continuar con el filtro si no hubo errores
        chain.doFilter(request, response);
    }
}
