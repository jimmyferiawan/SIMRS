package com.simrs.jimmy.security;

import com.simrs.jimmy.entity.Petugas;
import com.simrs.jimmy.repository.PetugasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    PetugasRepository petugasRepository;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        Optional<Petugas> petugas = Optional.ofNullable(petugasRepository.findByNipAndPassword(username, pwd));
        if (petugas.isPresent()) {
            return new UsernamePasswordAuthenticationToken(username, pwd, Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
        } else {
            throw new BadCredentialsException("User authentication failed!!!!");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
