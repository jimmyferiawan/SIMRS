package com.simrs.jimmy;

import com.simrs.jimmy.entity.constenum.GolonganDarah;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CobaEnum {
    public static void main(String[] args) {
        final long JWT_TOKEN_VALIDITY = 5*60*60;
        Map<String, Object> claims = new HashMap<>();
        Date sekarang = new Date(System.currentTimeMillis());
        Date validJwt = new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY*1000);

        String token = Jwts.builder().setClaims(claims).setSubject("19980730202012000").setIssuedAt(sekarang)
                .setExpiration(validJwt).signWith(SignatureAlgorithm.HS512, "_12jI-2hkLkj8hgvzaR23Bp").compact();

        Claims claims1 = Jwts.parser().setSigningKey("hdbf124!&-KjBC").parseClaimsJws(token).getBody();
        System.out.println("sekarang: " +  sekarang);
        System.out.println("validJwt: " + validJwt);
        System.out.println(claims1.getExpiration() + " before " + sekarang +": " + claims1.getExpiration().before(sekarang));
//        System.out.println("sebelum: " + .Before(sekarang));
    }
}
