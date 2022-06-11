package com.simrs.jimmy.repository;

import com.simrs.jimmy.entity.Obat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObatRepository extends JpaRepository<Obat, String> {
    Obat findByKodeObat(String kodeObat);
}
