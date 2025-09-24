package com.showhive.auth;

import com.showhive.ShowHiveException;
import com.showhive.exception.ErrorCode;
import com.showhive.member.domain.Member;
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
import java.util.Date;

@Component
@RequiredArgsConstructor
public class TokenParser {

    @Value("${jwt.secret}")
    private String secretKey;

    public static final long ACCESS_TOKEN_EXP = 60L * 60L * 1000L; // 1시간
    public static final long REFRESH_TOKEN_EXP = 60L * 60L * 1000L * 24L * 30L; // 1달
    private static final String PREFIX = "Bearer ";

    public String createAccessToken(Member member) {
        return createToken(member, ACCESS_TOKEN_EXP, "ACCESS_TOKEN");
    }

    public String createRefreshToken(Member member) {
        return createToken(member, REFRESH_TOKEN_EXP, "REFRESH_TOKEN");
    }

    private String createToken(Member member, long expirationTime, String tokenType) {
        Date expirationDate = calculateExpirationDateFromNow(expirationTime);

        return Jwts.builder()
                .setSubject(member.getId().toString())
                .claim("role", member.getRole())
                .claim("token_type", tokenType)
                .setExpiration(expirationDate)
                .signWith(createSigningKey())
                .compact();
    }

    private Date calculateExpirationDateFromNow(long expirationTime) {
        long currentDateTime = new Date().getTime();
        return new Date(currentDateTime + expirationTime);
    }

    public long parseToken(String token) {
        try {
            if (token == null || !token.startsWith(PREFIX)) {
                throw new ShowHiveException(ErrorCode.INVALID_TOKEN.getMessage(), ErrorCode.INVALID_TOKEN.getStatusCode());
            }
            token = token.substring(PREFIX.length());
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
