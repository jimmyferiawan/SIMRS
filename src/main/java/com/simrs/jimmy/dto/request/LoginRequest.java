package com.simrs.jimmy.dto.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String nip;
    private String password;
}
