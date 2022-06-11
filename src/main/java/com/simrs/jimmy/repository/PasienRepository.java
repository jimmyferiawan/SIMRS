package com.simrs.jimmy.repository;

import com.simrs.jimmy.entity.Pasien;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasienRepository extends JpaRepository<Pasien, String> {
    Pasien findByNoRekamMedis(String noRkmMed);
}
