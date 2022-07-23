package com.simrs.jimmy.repository;

import com.simrs.jimmy.entity.Petugas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface PetugasRepository extends JpaRepository<Petugas, String>, PetugasRepositoryCustom {
    Petugas findByNip(String nip);
    Petugas findByNipAndPassword(String nip, String password);
}
