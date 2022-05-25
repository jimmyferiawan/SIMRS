package com.simrs.jimmy.service;

import com.simrs.jimmy.entity.Petugas;

import java.util.List;

public interface PetugasService {
    Petugas createPetugas(Petugas petugas);
    Petugas readPetugasById(String nip);
    List<Petugas> readAllPetugas();
    void updatePetugas(Petugas petugas, String nip);
    void deletePetugas(String nip);
}
