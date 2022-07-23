package com.simrs.jimmy.service.impl;

import com.simrs.jimmy.entity.Petugas;
import com.simrs.jimmy.service.PetugasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    PetugasService petugasService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<SimpleGrantedAuthority> roles = null;
        roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
        Petugas petugas = petugasService.readPetugasById(username);

        return new User(petugas.getNip(), petugas.getPassword(), roles);
    }
}
