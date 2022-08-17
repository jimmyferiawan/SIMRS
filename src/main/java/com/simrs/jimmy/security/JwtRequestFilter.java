//package com.simrs.jimmy.security;
//
//import io.jsonwebtoken.*;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerExceptionResolver;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Optional;
//
//@Component
//@Slf4j
//public class JwtRequestFilter extends HttpFilter {
//
//    @Autowired
//    JwkTokenUtil jwkTokenUtil;
//
//    @Autowired
//    @Qualifier("handlerExceptionResolver")
//    private HandlerExceptionResolver resolver;
//
//    @Override
//    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//
//        Optional<String> reqTokenHeader = Optional.ofNullable(request.getHeader("Authorization"));
//        String username = null;
//        String jwtToken = null;
//        log.info(request.getServletPath());
//        if(request.getServletPath().equals("/otentikasi/signin")) {
//            filterChain.doFilter(request, response);
//        }else {
//            if (reqTokenHeader.isPresent()) {
//                if (reqTokenHeader.get().startsWith("Bearer ")) {
//                    jwtToken = reqTokenHeader.get().substring(7);
//                    Jws jws = null;
//                    try {
//                        jws = Jwts.parser().setSigningKey(jwkTokenUtil.getSecret()).parseClaimsJws(jwtToken);
//                    } catch (ExpiredJwtException e) {
//                        resolver.resolveException(request, response, null, e);
//                    } catch (MalformedJwtException e) {
//                        resolver.resolveException(request, response, null, e);
//                    }
//                    filterChain.doFilter(request, response);
//                } else {
//                    resolver.resolveException(request, response, null, new JwtException("Bearer Token not found"));
//                }
//            } else {
//                resolver.resolveException(request, response, null, new JwtException("Authorization not found"));
//            }
//        }
//    }
//
//
//}
