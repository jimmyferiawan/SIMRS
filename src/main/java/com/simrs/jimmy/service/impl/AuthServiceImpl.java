package com.simrs.jimmy.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simrs.jimmy.dto.request.LoginRequest;
import com.simrs.jimmy.entity.Petugas;
import com.simrs.jimmy.repository.PetugasRepository;
import com.simrs.jimmy.security.JwkTokenUtil;
import com.simrs.jimmy.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    JwkTokenUtil jwkTokenUtil;
    @Autowired
    PetugasRepository petugasRepository;

    @Override
    public String userLogin(LoginRequest loginRequest) {
        Optional<Petugas> petugas = Optional.ofNullable(petugasRepository.findByNipAndPassword(loginRequest.getNip(), loginRequest.getPassword()));

        System.out.println(petugas.isPresent());
        if (!petugas.isPresent()) {
            throw new EntityNotFoundException(loginRequest.getNip());
        }
        try {
            System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(petugas.get()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jwkTokenUtil.generateToken(petugas.get());
    }

    @Override
    public Petugas loadByTokenPetugas(String token) {
        String nip = jwkTokenUtil.getUsernameFromToken(token);
        Optional<Petugas> petugas = Optional.ofNullable(petugasRepository.findByNip(nip));
        if(!petugas.isPresent()) throw new EntityNotFoundException(nip);

        return petugas.get();
    }
}
