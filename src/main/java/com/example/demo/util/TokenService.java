package com.example.demo.util;

import com.example.demo.entity.user.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class TokenService {

    private String secret = "984hg493gh0439rthr0429uruj2309yh937gc763fe87t3f89723gf";

    public String getUsername(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    public Object getRoles(String token) {
        return getAllClaimsFromToken(token).get("roles", List.class).get(0);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
}
