package com.simrs.jimmy.controller;

import com.simrs.jimmy.dto.request.LoginRequest;
import com.simrs.jimmy.dto.response.BaseResponse;
import com.simrs.jimmy.dto.response.format.SuccessResponse;
import com.simrs.jimmy.dto.response.format.TokenResponse;
import com.simrs.jimmy.entity.Petugas;
import com.simrs.jimmy.security.JwkTokenUtil;
import com.simrs.jimmy.service.PetugasService;
import com.simrs.jimmy.service.impl.CustomUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/auth")
@Slf4j
//TODO: implements refresh token
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    CustomUserDetailsService customUserDetailsService;
    @Autowired
    JwkTokenUtil jwkTokenUtil;

    @PostMapping("/signin")
    public ResponseEntity<BaseResponse> loginUser(HttpServletRequest request, @RequestBody @Valid @NotNull LoginRequest loginRequest) throws Exception {
        log.info("=====================================================");
        log.info("POST " + request.getServletPath());
        log.info("-----------------------------------------------------");
        log.info("Request");
        log.info(loginRequest.toString());
        log.info("-----------------------------------------------------");
        log.info("=====================================================");
//        Petugas petugas = authService.userLogin(loginRequest);
//        return ResponseEntity.ok().body(petugas);
//        String token = authService.userLogin(loginRequest);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getNip(), loginRequest.getPassword()));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginRequest.getNip());
        String token = jwkTokenUtil.generateToken(userDetails);

        return  ResponseEntity.ok().body(new BaseResponse(true, "login sukses", SuccessResponse.build(TokenResponse.build(token))));
    }

}
