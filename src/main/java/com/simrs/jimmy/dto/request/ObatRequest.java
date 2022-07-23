package com.simrs.jimmy.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class ObatRequest {
    @Size(max = 15)
    private String namaObat;
    @Size(max = 12)
    private Double harga;
}
