package com.simrs.jimmy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "obat")
public class Obat {
    @Id @Size(max = 15) @Column(name = "kode_obat")
    private String kodeObat;
    @Column(name = "nama_obat")
    private String namaObat;
    @Column(name = "harga")
    private Double harga;
}
