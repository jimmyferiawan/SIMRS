package com.simrs.jimmy.controller;

import com.simrs.jimmy.dto.LoginRequest;
import com.simrs.jimmy.dto.format.BaseResponse;
import com.simrs.jimmy.service.PetugasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//@RequestMapping("/auth")
//public class AuthController {
//    
//    @Autowired
//    PetugasService petugasService;
//    
//    @PostMapping
//    public ResponseEntity<BaseResponse> loginUser(@RequestBody LoginRequest loginRequest) {
//        
//        return ResponseEntity.ok().body();
//    }
//}
