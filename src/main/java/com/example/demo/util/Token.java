package com.example.demo.util;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@UtilityClass
public class Token {

    private final String secret = "984hg493gh0439rthr0429uruj2309yh937gc763fe87t3f89723gf";

    private final long jwtLifeTime = 86400000;

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
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
