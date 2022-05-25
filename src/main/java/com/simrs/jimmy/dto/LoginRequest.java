package com.simrs.jimmy.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String nip;
    private String password;
}
