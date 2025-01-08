package com.cha.priceradar.security.jwt;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;

import com.cha.priceradar.common.domain.UserRole;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.Key;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Properties;

@Component
public class JwtProvider {

    @Value("${jwt.access.key}")
    private String accessKeyStr;
    @Value("${jwt.refresh.key}")
    private String refreshKeyStr;

    @Getter
    private final String grantType = "Bearer";
    private final DateTimeFormatter dateTimeFormatter = ISO_LOCAL_DATE;

    private Key accessKey;
    private Key refreshKey;

    @PostConstruct
    public void init() {
        this.accessKey = decodeKey(accessKeyStr);
        this.refreshKey = decodeKey(refreshKeyStr);
    }

    public Optional<String> resolveToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");


        if (authorizationHeader != null && authorizationHeader.startsWith(grantType+" "))
            return Optional.of(authorizationHeader.substring(grantType.length()+1));

        return Optional.empty();
    }

    private Key decodeKey(String keyStr) {
        return Keys.hmacShaKeyFor(keyStr.getBytes());
    }

    public TokenInfo generateToken(TokenableInfo payload) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime accessExpiredTime = now.plusHours(24);
        LocalDateTime refreshExpiredTime = now.plusDays(30);

        String accessToken = Jwts.builder()
                .setSubject(payload.getId().toString())
                .claim("email", payload.getEmail())
                .claim("username", payload.getUsername())
                .claim("role", payload.getUserRole())
                .setIssuedAt(Timestamp.valueOf(now))
                .setExpiration(Timestamp.valueOf(accessExpiredTime))
                .signWith(accessKey, SignatureAlgorithm.HS256)
                .compact();

        String refreshToken = Jwts.builder()
                .setSubject(payload.getId().toString())
                .setIssuedAt(Timestamp.valueOf(now))
                .setExpiration(Timestamp.valueOf(refreshExpiredTime))
                .signWith(refreshKey, SignatureAlgorithm.HS256)
                .compact();

        return TokenInfo.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .grantType(grantType)
                .build();
    }

    public TokenStatus validateAccessToken(String token) {
        return validateToken(accessKey, token);
    }

    public TokenStatus validateRefreshToken(String token) {
        return validateToken(refreshKey, token);
    }

    private TokenStatus validateToken(Key key, String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return TokenStatus.VALID;
        } catch (ExpiredJwtException e) {
            return TokenStatus.EXPIRED;
        } catch (Exception e) {
            return TokenStatus.INVALID;
        }
    }

    public TokenableInfo parseAccessToken(String token) {
        Claims claims = getClaims(accessKey, token);
        if (claims == null)
            return null;

        return TokenableUser.builder()
                .id(Long.valueOf(claims.getSubject()))
                .email((String) claims.get("email"))
                .username((String) claims.get("username"))
                .userRole(UserRole.valueOf((String) claims.get("role")))
                .build();
    }

    private Claims getClaims(Key key, String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException | IllegalArgumentException e) {
            return null;
        }
    }
}
