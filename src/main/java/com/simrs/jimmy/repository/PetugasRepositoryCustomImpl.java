package com.simrs.jimmy.repository;

import com.simrs.jimmy.entity.Petugas;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.sql.SQLIntegrityConstraintViolationException;

@Component
public class PetugasRepositoryCustomImpl implements PetugasRepositoryCustom {
    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public void create(Petugas petugas) {
        System.out.println("PetugasRepositoryCustom()->create(Petugas)");
        try {
            em.persist(petugas);
        } catch (Exception ex) {
            try {
                throw new SQLIntegrityConstraintViolationException(petugas.getNip());
            } catch (SQLIntegrityConstraintViolationException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Petugas read(String nip) {
        System.out.println("PetugasRepositoryCustom()->read(Petugas)");
        Petugas petugas = new Petugas();
        petugas = em.find(Petugas.class, nip);
        if(petugas == null) {
            System.out.println("petugas null");
            throw new EntityNotFoundException(nip);
        }

        return petugas;
    }

    @Override
    public void update(Petugas petugas) {
        System.out.println("PetugasRepositoryCustom()->update(Petugas)");
    }

    @Override
    public void delete(String nip) {
        System.out.println("PetugasRepositoryCustom()->delete(Petugas)");
    }
}