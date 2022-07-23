package com.simrs.jimmy.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class PetugasRequest {
    @NotNull(message = "NIP tidak boleh kosong")
    @NotBlank(message = "NIP tidak boleh kosong")
    @Size(max = 20, message = "NIP harus kurang dari 20 karakter")
    private String nip;

    @NotEmpty(message = "Nama tidak boleh kosong")
    @NotBlank(message = "Nama tidak boleh kosong")
    @Size(max = 40, message = "Nama harus kurang dari 40 karakter")
    private String nama;

    @NotNull(message = "Jenis Kelamin tidak boleh kosong")
    @Pattern(regexp = "[L|P]+", message = "pilih L/P")
    private String jk;

    @NotNull(message = "Tempat lahir tidak boleh kosong")
    @Size(max = 15, message = "Tempat lahir harus kurang dari 15 karakter")
    private String tmp_lahir;

    @NotNull(message = "tanggal lahir tidak boleh kosong atau format tanggal salah")
//    @Pattern(regexp = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[0-1])$", message = "Format tanggl salah yyyy-MM-dd (ex 2012-01-30)")
    @JsonFormat(timezone = "Asia/Bangkok")
    private Date tgl_lahir;

    private String alamat;

    @NotNull(message = "password tidak boleh kosong")
    private String password;

    public void setTgl_lahir(String tgl_lahir) {
        try {
            this.tgl_lahir = new SimpleDateFormat("yyyy-MM-dd").parse(tgl_lahir);
        } catch (ParseException e) {

            System.out.println("PetugasRequest.setTgl_lahir: ParseException error");
            e.printStackTrace();
        }
    }

    public Date getTgl_lahir() {
        return tgl_lahir;
    }
}

