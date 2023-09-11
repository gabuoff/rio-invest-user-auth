package com.rioinvest.authms.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.rioinvest.authms.domain.user.User;

@Service
public class TokenService {


    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API rio.invest.user.auth").withSubject(user.getUsername())
                    .withClaim("id", user.getId())
                    .withExpiresAt(dataExpiresAt())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error generating token", exception);
        }
    }

    private Instant dataExpiresAt() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String getSubject(String tokenJWT) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                .withIssuer("API rio.invest.user.auth")
                .build()
                .verify(tokenJWT)
                .getSubject();
        } catch (JWTVerificationException e) {
            throw new RuntimeException("Token JWT invalid or expired");
        }
    }
}
