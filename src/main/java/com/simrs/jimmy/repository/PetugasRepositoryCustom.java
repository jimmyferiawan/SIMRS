package com.simrs.jimmy.repository;

import com.simrs.jimmy.entity.Petugas;

public interface PetugasRepositoryCustom {
    void create(Petugas petugas);
    Petugas read(String nip);
    void update(Petugas petugas);
    void delete(String nip);
}
