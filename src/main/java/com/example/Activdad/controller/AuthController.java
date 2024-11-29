package com.example.Activdad.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Key;
import java.util.Collections;
import java.util.Date;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "1234";
    private static final String SECRET_KEY = "my-secret-key-which-is-very-secure"; // Asegúrate de que la clave sea segura

    // Define el rol para el usuario
    private static final String ROLE = "ADMIN"; // El rol del usuario

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        if (USERNAME.equals(request.getUsername()) && PASSWORD.equals(request.getPassword())) {
            // Generar el token JWT con el rol
            String token = Jwts.builder()
                    .setSubject(USERNAME)  // Usuario autenticado
                    .claim("role", ROLE)   // Agregar el rol al token
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 86400000))  // 1 día de expiración
                    .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))  // Firma con la clave secreta
                    .compact();

            // Retornar el token al cliente
            return ResponseEntity.ok(Collections.singletonMap("token", token));
        }

        return ResponseEntity.status(401).body("Credenciales inválidas");
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @Data
    public static class LoginRequest {
        private String username;
        private String password;
    }
}
