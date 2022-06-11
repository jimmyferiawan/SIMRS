package com.simrs.jimmy.dto.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String nip;
    private String password;

    public String toJson() {
        return "{nip: " + this.nip + ", password: " + this.password +"}";
    }
}
