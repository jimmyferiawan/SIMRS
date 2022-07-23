package com.simrs.jimmy.service;

import com.simrs.jimmy.entity.Obat;
import com.simrs.jimmy.repository.ObatRepository;

import java.util.List;

public interface ObatService {
    Obat createObat(Obat obat);
    List<Obat> readAllObat();
    Obat readObatById(String kodeObat);
    void updateObat(String kodeObat, Obat obat);
    void deleteObat(String kodeObat);
}
