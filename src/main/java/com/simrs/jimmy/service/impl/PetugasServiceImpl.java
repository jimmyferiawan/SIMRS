package com.simrs.jimmy.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simrs.jimmy.entity.Petugas;
import com.simrs.jimmy.repository.PetugasRepository;
import com.simrs.jimmy.service.PetugasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class PetugasServiceImpl implements PetugasService {
    @Autowired
    public PetugasRepository petugasRepository;

    @Override
    public Petugas createPetugas(Petugas petugas) {
        System.out.println("exist by id: " + petugasRepository.existsById(petugas.getNip()));
        if(petugasRepository.existsById(petugas.getNip().trim())) {
            throw new EntityExistsException(petugas.getNip());
        };

        return petugasRepository.save(petugas);
    }

    @Override
    public Petugas readPetugasById(String nip) {
        Petugas petugas = petugasRepository.findByNip(nip);
        if(petugas == null) {
            throw new EntityNotFoundException(nip);
        }
        try {
            System.out.println("petugas: " + new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(petugas));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return petugas;
    }

    @Override
    public List<Petugas> readAllPetugas() {
        return petugasRepository.findAll();
    }

    @Override
    public void updatePetugas(Petugas petugas, String nip) {
        Petugas petugasExist = petugasRepository.findById(nip).orElseThrow(() ->
                new EntityNotFoundException(nip));
        petugasExist.setNama(petugas.getNama().trim());
        petugasExist.setJk(petugas.getJk());
        petugasExist.setTmp_lahir(petugas.getTmp_lahir());
        petugasExist.setTgl_lahir(petugas.getTgl_lahir());
        petugasExist.setAlamat(petugas.getAlamat());
        petugasExist.setPassword(petugas.getPassword());

        petugasRepository.save(petugasExist);
    }

    @Override
    public void deletePetugas(String nip) {
        Petugas petugasExist = petugasRepository.findById(nip).orElseThrow(() ->
                new EntityNotFoundException(nip));
        petugasRepository.delete(petugasExist);
    }



//    public void create() {
//        Date tglLahir = new Date();
//        try {
//            tglLahir = new SimpleDateFormat("yyyy-MM-dd").parse("2000-01-15");
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        Petugas petugas = new Petugas(PetugasRequest.build("19980730202012000", "junaedi", "L", "Kediri", tglLahir, "Kediri", "123456"));
//        petugasRepository.create(petugas);
//    }
//
//    public Petugas read(String nip) {
//        return petugasRepository.read(nip);
//    }
}
