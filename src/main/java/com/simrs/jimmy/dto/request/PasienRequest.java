package com.simrs.jimmy.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.simrs.jimmy.entity.Pasien;
import com.simrs.jimmy.entity.constenum.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
public class PasienRequest {
    @Size(max = 10, message = "nomor rekam medis lebih dari 10 karakter")
    private String noRekamMedis;
    @Size(max = 40, message = "nama pasien lebih dari 40 karakter")
    private String namaPasien;
    @Size(max = 20, message = "nomor KTP lebih dari 20 karakter")
    private String noKtp;
    @Pattern(regexp = "^L$|^P$", message = "L | P")
    private String jenisKelamin;
    @Size(max = 15, message = "tempat lahir lebih dari 15 karakter")
    private String tempatLahir;
    @JsonFormat(timezone = "Asia/Bangkok")
    private Date tanggalLahir;
    @Size(max = 200, message = "alamat lebih dari 200 karakter")
    private String alamat;
    // ENUM('A','B','AB','O','-')
    @Pattern(regexp = "^A$|^B$|^AB$|^O$", message = "A | B | AB | O")
    private String golonganDarah;
    @Size(max = 15, message = "pekerjaan lebih dari 15 karakter")
    private String pekerjaan;
    @Pattern(regexp = "^single$|^menikah$|^janda$|^duda$", message = "single | menikah | janda | duda")
    private String statusNikah;
    @Size(max = 12, message = "agama lebih dari 12 karakter")
    private String agama;
    @JsonFormat(timezone = "Asia/Bangkok")
    private Date tanggalDaftar;
    @Size(max = 13, message = "nomor telepon lebih dari 13 karakter")
    private String nomorTelepon;
    @Size(max = 20, message = "umur lebih dari 20 karakter")
    private String umur;
    // ENUM('TK','SD','SMP','SMA','D1','D2','D3','D4','S1','S2','S3','-')
    @Pattern(regexp = "^TK$|^SD$|^SMP$|^SMA$|^D1$|^D2$|^D3$|^D4$|^S1$|^S2$|^S3$", message = "TK | SD | SMP | SMA | D1 | D2 | D3 | D4 | S1 | S2 | S3")
    private String pendidikan;
    // ENUM('ayah','ibu','istri','suami','saudara','lain-lain')
    @Pattern(regexp = "^ayah$|^ibu$|^istri$|^suami$|^saudara$", message = "ayah | ibu | istri | suami | saudara")
    private String keluarga;
    @Size(max = 50, message = "nama keluarga lebih dari 50 karakter")
    private String namaKeluarga;

    public Pasien toPasien() {
        return Pasien.build()
                .setNoRekamMedis(this.noRekamMedis)
                .setNamaPasien(this.namaPasien)
                .setNoKtp(this.noKtp)
                .setJenisKelamin(JK.valueOf(this.jenisKelamin))
                .setTempatLahir(this.getTempatLahir())
                .setTanggalLahir(this.getTanggalLahir())
                .setAlamat(this.alamat)
                .setGolonganDarah(GolonganDarah.valueOf(this.golonganDarah))
                .setPekerjaan(this.pekerjaan)
                .setStatusNikah(StatusNikah.valueOf(this.statusNikah))
                .setAgama(this.agama)
                .setTanggalDaftar(this.tanggalDaftar)
                .setNomorTelepon(this.nomorTelepon)
                .setUmur(this.umur)
                .setPendidikan(Pendidikan.valueOf(this.pendidikan))
                .setKeluarga(Keluarga.valueOf(this.keluarga))
                .setNamaKeluarga(this.namaKeluarga);

    }

    public String toJson() {
        return "PasienRequest{" +
                "noRekamMedis: \"" + noRekamMedis + "\"," +
                "namaPasien: \"" + namaPasien + "\"," +
                "noKtp: \"" + noKtp + "\"," +
                "jenisKelamin: \"" + jenisKelamin + "\"," +
                "tempatLahir: \"" + tempatLahir + "\"," +
                "tanggalLahir: \"" + tanggalLahir + "\","+
                "alamat: \"" + alamat + "\"," +
                "golonganDarah: \"" + golonganDarah + "\"," +
                "pekerjaan: \"" + pekerjaan + "\"," +
                "statusNikah: \"" + statusNikah + "\"," +
                "agama: \"" + agama + "\"," +
                "tanggalDaftar: \"" + tanggalDaftar + "\","+
                "nomorTelepon: \"" + nomorTelepon + "\"," +
                "umur: \"" + umur + "\"," +
                "pendidikan: \"" + pendidikan + "\"," +
                "keluarga: \"" + keluarga + "\"," +
                "namaKeluarga: \"" + namaKeluarga + "\"," +
                '}';
    }
}
