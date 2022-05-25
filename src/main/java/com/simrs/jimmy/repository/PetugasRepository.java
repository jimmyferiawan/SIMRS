package com.simrs.jimmy.repository;

import com.simrs.jimmy.entity.Petugas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetugasRepository extends JpaRepository<Petugas, String>, PetugasRepositoryCustom {
    Petugas findByNip(String nip);
}
