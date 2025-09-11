package com.showhive.auth.application;

import com.showhive.ShowHiveException;
import com.showhive.exception.ErrorCode;
import com.showhive.member.domain.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.WeakKeyException;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenManager {

    @Value("${jwt.secret}")
    private String secretKey;

    public static final long ACCESS_TOKEN_EXP = 60L * 60L * 1000L; // 1시간
    public static final long REFRESH_TOKEN_EXP = 60L * 60L * 1000L * 24L * 30L; // 1달

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
                //.claim("role", member.getRole()) // TODO : member role 추가해야됨
                .claim("token_type", tokenType)
                .setExpiration(expirationDate)
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();
    }

    private Date calculateExpirationDateFromNow(long expirationTime) {
        long currentDateTime = new Date().getTime();
        return new Date(currentDateTime + expirationTime);
    }

    public long parseToken(String token) {
        try {
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
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
