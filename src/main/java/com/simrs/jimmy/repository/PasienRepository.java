package com.simrs.jimmy.repository;

import com.simrs.jimmy.entity.Pasien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasienRepository extends JpaRepository<Pasien, String> {
    Pasien findByNoRekamMedis(String noRkmMed);
}
