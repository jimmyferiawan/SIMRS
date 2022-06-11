package com.simrs.jimmy.service.impl;

import com.simrs.jimmy.entity.Pasien;
import com.simrs.jimmy.repository.PasienRepository;
import com.simrs.jimmy.service.PasienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class PasienServiceImpl implements PasienService {

    @Autowired
    PasienRepository pasienRepository;

    @Override
    public Pasien createPasien(Pasien pasien) {
        System.out.println("Pasien()->createPasien()");
        if(pasienRepository.existsById(pasien.getNoRekamMedis().trim())) {
            throw new EntityExistsException(pasien.getNoRekamMedis().trim());
        }

        return pasienRepository.save(pasien);
    }

    @Override
    public Pasien getPasienById(String noRkmMed) {
        System.out.println("Pasien()->getPasienById()");
        Optional<Pasien> pasien = Optional.ofNullable(pasienRepository.findByNoRekamMedis(noRkmMed.trim()));

        if(!pasien.isPresent()) {
            throw new EntityNotFoundException(noRkmMed);
        }

        return pasien.get();
    }

    @Override
    public List<Pasien> getAllPasien() {
        System.out.println("Pasien()->getAllPasien()");

        return pasienRepository.findAll();
    }

    @Override
    public void updatePasien(Pasien pasien, String noRkmMed) {
        System.out.println("Pasien()->updatePasien()");
        noRkmMed = noRkmMed.trim();
        if(!pasienRepository.existsById(noRkmMed)) throw new EntityNotFoundException(noRkmMed);
        pasien.setNoRekamMedis(noRkmMed);
        pasienRepository.save(pasien);
    }

    @Override
    public void deletePasien(String noRkmMed) {
        System.out.println("Pasien()->deletePasien()");
        noRkmMed = noRkmMed.trim();
        if(!pasienRepository.existsById(noRkmMed)) throw new EntityNotFoundException(noRkmMed);
        pasienRepository.deleteById(noRkmMed);
    }
}
