package com.simrs.jimmy.service;

import com.simrs.jimmy.entity.Pasien;

import java.util.List;

public interface PasienService{
    Pasien createPasien(Pasien pasien);
    Pasien getPasienById(String noRkmMed);
    List<Pasien> getAllPasien();
    void updatePasien(Pasien pasien, String noRkmMed);
    void deletePasien(String noRkmMed);
}
