package com.example.demo.util;

import com.example.demo.entity.user.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@UtilityClass
public class TokenService {

    @Value("${application.security.jwt.secret}")
    private String secret;

    public String getUsername(final String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    public Object getRoles(final String token) {
        return getAllClaimsFromToken(token).get("roles", List.class).get(0);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
}
