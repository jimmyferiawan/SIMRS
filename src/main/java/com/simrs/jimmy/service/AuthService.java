package com.simrs.jimmy.service;

import com.simrs.jimmy.dto.request.LoginRequest;
import com.simrs.jimmy.entity.Petugas;

public interface AuthService {
    String userLogin(LoginRequest loginRequest);
    Petugas loadByTokenPetugas(String token);
}
