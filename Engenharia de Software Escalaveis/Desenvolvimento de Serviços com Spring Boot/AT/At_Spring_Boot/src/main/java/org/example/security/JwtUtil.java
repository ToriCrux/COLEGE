package org.example.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {

    private static final String SECRET = "7hHf$L9s&Nq@tBz!rD3eXwVpYkAqJmNc";
    private static final long EXPIRATION = 1000 * 60 * 60 * 72;

    private final SecretKey secretKey = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    public String gerarToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .addClaims(Map.of("role", "PROFESSOR"))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public String validarToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String extrairRole(String token) {
        return (String) Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("role");
    }
}
