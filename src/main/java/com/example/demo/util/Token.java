package com.example.demo.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.Map;

@UtilityClass
public class Token {

    @Value("${application.security.jwt.secret}")
    private String secret;

    @Value("${application.security.jwt.lifetime}")
    private long jwtLifeTime;

    public String generateToken(final UserDetails userDetails) {
        Date issuedAt = new Date();
        return Jwts.builder()
                .setClaims(
                        Map.of(
                                "roles",
                                userDetails.getAuthorities()
                                        .stream()
                                        .map(GrantedAuthority::getAuthority)
                                        .toList()
                        )
                )
                .setSubject(userDetails.getUsername())
                .setIssuedAt(issuedAt)
                .setExpiration(new Date(issuedAt.getTime() + jwtLifeTime))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
}
