package com.simrs.jimmy.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "petugas")
public class Petugas {

    @Id
    @Column(nullable = false, length = 20)
    private String nip;

    @Column(length = 40, nullable = true)
    private String nama;

    @Column
    @Enumerated(EnumType.STRING)
    private JK jk;

    @Column(name = "tmp_lahir", length = 15)
    private String tmp_lahir;

    @Column(name = "tgl_lahir", nullable = true)
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Bangkok")
    private Date tgl_lahir;

    @Column(length = 200, nullable = true)
    private String alamat;

    @Column(length = 200, nullable = true)
    private String password;

    public Petugas(){

    }

    public Petugas(String nip, String nama, JK jk, String tmp_lahir, Date tgl_lahir, String alamat, String password) {
        this.nip = nip;
        this.nama = nama;
        this.jk = jk;
        this.tmp_lahir = tmp_lahir;
        this.tgl_lahir = tgl_lahir;
        this.alamat = alamat;
        this.password = password;
    }

    public Petugas(String nip, String nama, JK jk, String tmp_lahir, String tgl_lahir, String alamat, String password) {
        this.nip = nip;
        this.nama = nama;
        this.jk = jk;
        this.tmp_lahir = tmp_lahir;
        try {
            this.tgl_lahir = new SimpleDateFormat("yyyy-MM-dd").parse(tgl_lahir);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.alamat = alamat;
        this.password = password;
    }

    public Petugas(String nip, String nama, String jk, String tmp_lahir, Date tgl_lahir, String alamat, String password) {
        this.nip = nip;
        this.nama = nama;
        this.jk = JK.valueOf(jk);
        this.tmp_lahir = tmp_lahir;
        this.tgl_lahir = tgl_lahir;
        this.alamat = alamat;
        this.password = password;
    }

    public Petugas(String nip, String nama, String jk, String tmp_lahir, String tgl_lahir, String alamat, String password) {
        this.nip = nip;
        this.nama = nama;
        this.jk = JK.valueOf(jk);
        this.tmp_lahir = tmp_lahir;
        try {
            this.tgl_lahir = new SimpleDateFormat("yyyy-MM-dd").parse(tgl_lahir);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.alamat = alamat;
        this.password = password;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public JK getJk() {
        return jk;
    }

    public void setJk(JK jk) {
        this.jk = jk;
    }

    public void setJk(String jk) {
        try {
            this.jk = JK.valueOf(jk);
        } catch (Exception e) {
            System.out.println("error");
            this.jk = null;
        }
    }

    public String getTmp_lahir() {
        return tmp_lahir;
    }

    public void setTmp_lahir(String tmp_lahir) {
        this.tmp_lahir = tmp_lahir;
    }

    public Date getTgl_lahir() {
        return tgl_lahir;
    }

    public void setTgl_lahir(Date tgl_lahir) {
        this.tgl_lahir = tgl_lahir;
    }

    public void setTgl_lahir(String tgl_lahir) {
        try {
            this.tgl_lahir = new SimpleDateFormat("yyyy-MM-dd").parse(tgl_lahir);
        } catch (ParseException e) {
            System.out.println("PetugasRequest.setTgl_lahir: ParseException error");
            e.printStackTrace();
        }
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
