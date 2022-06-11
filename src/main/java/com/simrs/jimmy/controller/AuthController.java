package com.simrs.jimmy.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simrs.jimmy.dto.request.LoginRequest;
import com.simrs.jimmy.dto.response.BaseResponse;
import com.simrs.jimmy.dto.response.format.SuccessResponse;
import com.simrs.jimmy.entity.Petugas;
import com.simrs.jimmy.service.AuthService;
import com.simrs.jimmy.service.PetugasService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/otentikasi")
@Slf4j
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<BaseResponse> loginUser(HttpServletRequest request, @RequestBody @Valid @NotNull LoginRequest loginRequest) {
        String token = authService.userLogin(loginRequest);
        BaseResponse baseResponse = new BaseResponse(true, "login sukses", SuccessResponse.build(token));

        log.info("=====================================================");
        log.info("POST " + request.getServletPath());
        log.info("-----------------------------------------------------");
        log.info("Request");
        log.info(loginRequest.toJson());
        log.info("-----------------------------------------------------");
        log.info("Response");
        try {
            log.info(new ObjectMapper().writeValueAsString(baseResponse));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        log.info("-----------------------------------------------------");
        log.info("=====================================================");

        return  ResponseEntity.ok().body(baseResponse);
    }

}
