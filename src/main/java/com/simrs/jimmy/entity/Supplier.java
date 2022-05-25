package com.simrs.jimmy.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "supplier")
public class Supplier {
    @Column(name = "_id_supplier_ai")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idSupplierAi;
    @Id
    @Column(name = "id_supplier")
    private String idSupllier;
    @Column(name = "nama_supplier")
    private String namaSupplier;
    @Column(name = "alamat")
    private String alamat;


}
