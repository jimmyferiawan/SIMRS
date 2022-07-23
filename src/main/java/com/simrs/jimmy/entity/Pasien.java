package com.simrs.jimmy.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.simrs.jimmy.entity.constenum.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor(staticName = "build")
@Entity
@Table(name = "pasien")
public class Pasien {
    @Id @Column(nullable = false, length = 10, name = "no_rkm_medis")
    @Size(max = 10, message = "nomor rekam medis harus kurang dari 10 karakter")
    private String noRekamMedis;
    @Column(name = "nm_pasien", length = 40)
    @Size(max = 40, message = "nama pasien harus kurang dari 40 karakter")
    private String namaPasien;
    @Column(name = "no_ktp", length = 20)
    @Size(max = 20, message = "nomor KTP harus kurang dari 20 karakter")
    private String noKtp;
    @Column(name = "jk")
    @Enumerated(EnumType.STRING)
    private JK jenisKelamin;
    @Column(name = "tmp_lahir", length = 15)
    private String tempatLahir;
    @Column(name = "tgl_lahir")
    @JsonFormat(timezone = "Asia/Bangkok")
    private Date tanggalLahir;
    @Column(name = "alamat", length = 200)
    private String alamat;
    // ENUM('A','B','AB','O','-')
    @Column(name = "gol_darah") @Enumerated(EnumType.STRING)
    private GolonganDarah golonganDarah;
    @Column(name = "pekerjaan", length = 15)
    private String pekerjaan;
    @Column(name = "stts_nikah") @Enumerated(EnumType.STRING)
    // ENUM('single','menikah','janda','duda')
    private StatusNikah statusNikah;
    @Column(name = "agama", length = 12)
    private String agama;
    @Column(name = "tgl_daftar")
    @JsonFormat(timezone = "Asia/Bangkok")
    private Date tanggalDaftar;
    @Column(name = "no_tlp", length = 13)
    private String nomorTelepon;
    @Column(name = "umur", length = 20)
    private String umur;
    // ENUM('TK','SD','SMP','SMA','D1','D2','D3','D4','S1','S2','S3','-')
    @Column(name = "pnd") @Enumerated(EnumType.STRING)
    private Pendidikan pendidikan;
    // ENUM('ayah','ibu','istri','suami','saudara','lain-lain')
    @Column(name = "keluarga") @Enumerated(EnumType.STRING)
    private Keluarga keluarga;
    @Column(name = "namakeluarga", length = 50)
    private String namaKeluarga;
}
