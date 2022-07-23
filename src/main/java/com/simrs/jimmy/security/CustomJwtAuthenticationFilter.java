package com.simrs.jimmy.security;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomJwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwkTokenUtil jwkTokenUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwtToken = extractJwtFromRequest(request);
            if(StringUtils.hasText(jwtToken) && jwkTokenUtil.validateToken(jwtToken)) {
                UserDetails userDetails = new User(jwkTokenUtil.getUsernameFromToken(jwtToken), "", jwkTokenUtil.getRolesFromToken(jwtToken));
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            } else {
                System.out.println("Cannot set security context");
            }
        } catch (ExpiredJwtException ex) {
            System.out.println("ExpiredJwtException");
            String isRefreshToken = request.getHeader("isRefreshToken");
            String requestUrl = request.getRequestURL().toString();
            if(isRefreshToken != null && isRefreshToken.equals(true) && requestUrl.contains("refreshToken")) {
                allowRefreshToken(ex, request);
            } else {
                request.setAttribute("exception", ex);
            }
        } catch (BadCredentialsException ex) {
            System.out.println("BadCredentialsException");
            request.setAttribute("exception", ex);
        } catch (Exception ex) {
            System.out.println("Exception");
            ex.printStackTrace();
        }
        filterChain.doFilter(request, response);
    }

    private void allowRefreshToken(ExpiredJwtException ex, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(null, null, null);
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        request.setAttribute("claims", ex.getClaims());
    }

    private String extractJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }
}
