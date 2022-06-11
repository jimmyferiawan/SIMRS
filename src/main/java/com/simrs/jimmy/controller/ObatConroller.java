package com.simrs.jimmy.controller;

import com.simrs.jimmy.dto.request.ObatRequest;
import com.simrs.jimmy.dto.response.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping(name = "/obat")
public class ObatConroller {

    protected BaseResponse baseResponse;

    @GetMapping
    public ResponseEntity<BaseResponse> getAllObat(HttpServletRequest request) {

    }

    @GetMapping(name = "/{kodeObat}")
    public ResponseEntity<BaseResponse> getObatByID(HttpServletRequest request, @PathVariable("kodeObat") String kodeObat) {

    }

    @PostMapping
    public ResponseEntity<BaseResponse> addObat(HttpServletRequest request, @RequestBody @Valid ObatRequest obatRequest) {

    }

    @PutMapping(name = "/{kodeObat}")
    public ResponseEntity<BaseResponse> editObat(HttpServletRequest request, @PathVariable("kodeObat") String kodeObat, @RequestBody ObatRequest obatRequest) {

    }

    @DeleteMapping(name = "/{kodeObat}")
    public ResponseEntity<BaseResponse> removeObat(HttpServletRequest request, PathVariable("kodeObat") String kodeObat) {

    }
}
