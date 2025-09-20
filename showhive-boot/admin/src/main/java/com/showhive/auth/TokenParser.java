package com.showhive.auth;

import com.showhive.ShowHiveException;
import com.showhive.exception.ErrorCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;

@Component
@RequiredArgsConstructor
public class TokenParser {

    @Value("${jwt.secret}")
    private String secretKey;

    private static final String PREFIX = "Bearer ";

    public long parseToken(String token) {
        try {
            if (token != null && token.startsWith(PREFIX)) {
                token = token.substring(PREFIX.length());
            }
            Claims claims = parseClaims(token);
            return Long.parseLong(claims.getSubject());
        } catch (ExpiredJwtException exception) {
            throw new ShowHiveException(ErrorCode.EXPIRED_TOKEN.getMessage(), ErrorCode.EXPIRED_TOKEN.getStatusCode());
        } catch (JwtException exception) {
            throw new ShowHiveException(ErrorCode.INVALID_TOKEN.getMessage(), ErrorCode.INVALID_TOKEN.getStatusCode());
        }
    }

    private Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(createSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key createSigningKey() {
        byte[] secretKeyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(secretKeyBytes);
    }
}
