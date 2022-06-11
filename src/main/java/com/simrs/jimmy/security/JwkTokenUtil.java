package com.simrs.jimmy.security;

import com.simrs.jimmy.entity.Petugas;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class JwkTokenUtil implements Serializable {
    public static final long JWT_TOKEN_VALIDITY = 5*60*60;

    @Value("${jwt.secret}")
    private String secret;

    public String getSecret() {
        return secret;
    }

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    public String generateToken(@NotNull Petugas petugas) {
        Map<String, Object> claims = new HashMap<>();

        return doGenerateToken(claims, petugas.getNip());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY*1000)).signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    protected Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

//    public <T> T getClaimFromToken(String token, Function<Claims, T> claimResolver) {
//        Claims claims = getAllClaimsFromToken(token);
//
//        return claimResolver.apply(claims);
//    }

    public String getUsernameFromToken(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    public Date getIssuedAtDateFromToken(String token) {
        return getAllClaimsFromToken(token).getIssuedAt();
    }

    public Date getExpirationDateFromToken(String token) {
        return getAllClaimsFromToken(token).getExpiration();
    }

    public Boolean isTokenExpired(String token) {
        return getExpirationDateFromToken(token).before(new Date());
    }

    public boolean validateToken(String token, @NotNull Petugas petugas) {
        final String username = getUsernameFromToken(token);

        return (username.equals(petugas.getNip()) && !isTokenExpired(token));
    }

    public boolean validateToken(String token) {
        Jws jws = null;
        try {
            jws = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            return false;
        } catch (MalformedJwtException e) {
            return false;
        }
//        Claims claims = getAllClaimsFromToken(token);
        return true;
//        return !claims.getExpiration().before((new Date(System.currentTimeMillis())));
    }

    public String resolveToken(HttpServletRequest request) {
        Optional<String> bearerToken = Optional.ofNullable(request.getHeader("Authorization"));
        if(!bearerToken.isPresent()) {
            throw new JwtException("error auth header");
        }
//        if (bearerToken == null && !bearerToken.startsWith("Bearer ")) {
//            throw new JwtException("can not get token");
//        }

        return bearerToken.get().substring(7);
    }

}

