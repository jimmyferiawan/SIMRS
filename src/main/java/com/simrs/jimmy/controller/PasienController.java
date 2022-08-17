package com.simrs.jimmy.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simrs.jimmy.dto.request.PasienRequest;
import com.simrs.jimmy.dto.response.BaseResponse;
import com.simrs.jimmy.dto.response.format.ErrorResponse;
import com.simrs.jimmy.dto.response.format.SuccessResponse;
import com.simrs.jimmy.entity.Pasien;
import com.simrs.jimmy.service.PasienService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pasien")
@Slf4j
public class PasienController {
    @Autowired
    PasienService pasienService;
    BaseResponse resp;

    @GetMapping
    public ResponseEntity<GetAllPasien> getAllPasien(HttpServletRequest request) throws JsonProcessingException{
        log.info("================================================");
        GetAllPasien resp = new GetAllPasien(true, "Berhasil get all pasien", SuccessResponse.build(pasienService.getAllPasien()));
        log.info("GET " + request.getServletPath());
        log.info("------------------------------------------------");
        log.info("Response");
        log.info(new ObjectMapper().writeValueAsString(resp));
        log.info("------------------------------------------------");
        log.info("================================================");
        Optional<String> requestTokenHeader = Optional.ofNullable(request.getHeader("Authorization"));
        return ResponseEntity.ok().body(resp);
    }

    @GetMapping("/{noRekamMedis}")
    public ResponseEntity<GetPostPutPasien> getPasienById(HttpServletRequest request, @PathVariable String noRekamMedis) throws JsonProcessingException {
        GetPostPutPasien resp = new GetPostPutPasien(true, "Berhasil get pasien " + noRekamMedis, SuccessResponse.build(pasienService.getPasienById(noRekamMedis)));
        log.info("================================================");
        log.info("GET " + request.getServletPath());
        log.info("------------------------------------------------");
        log.info("Response");
        log.info(new ObjectMapper().writeValueAsString(resp));
        log.info("------------------------------------------------");
        log.info("================================================");

        return ResponseEntity.ok().body(resp);
    }

    @PostMapping
    public ResponseEntity<GetPostPutPasien> createPasien(HttpServletRequest request, @RequestBody @NonNull @Valid PasienRequest pasienRequest) throws JsonProcessingException{
        Pasien pasien = pasienService.createPasien(pasienRequest.toPasien());
//        Pasien pasien = pasienRequest.toPasien();

        GetPostPutPasien resp = new GetPostPutPasien(true, "Berhasil menambahkan pasien " + pasienRequest.getNoRekamMedis(), SuccessResponse.build(pasien));
        log.info("===============================================");
        log.info("POST " + request.getServletPath());
        log.info("------------------------------------------------");
        log.info("Request");
        log.info(new ObjectMapper().writeValueAsString(pasienRequest));
        log.info("------------------------------------------------");
        log.info("Response");
        log.info(new ObjectMapper().writeValueAsString(resp));
        log.info("------------------------------------------------");
        log.info("================================================");

        return ResponseEntity.ok().body(resp);
    }

    @PutMapping("/{noRekamMedis}")
    public ResponseEntity<GetPostPutPasien> editPasien(HttpServletRequest request, @RequestBody @NonNull @Valid PasienRequest pasienRequest, @PathVariable String noRekamMedis) throws JsonProcessingException{
        Pasien pasien = pasienRequest.toPasien().setNoRekamMedis(noRekamMedis);
        pasienService.updatePasien(pasien, noRekamMedis);

        GetPostPutPasien resp = new GetPostPutPasien(true, "Berhasil update pasien " + pasienRequest.getNoRekamMedis(), SuccessResponse.build(pasien));
        log.info("===============================================");
        log.info("PUT " + request.getServletPath());
        log.info("------------------------------------------------");
        log.info("Request");
        log.info(new ObjectMapper().writeValueAsString(pasienRequest));
        log.info("------------------------------------------------");
        log.info("Response");
        log.info(new ObjectMapper().writeValueAsString(resp));
        log.info("------------------------------------------------");
        log.info("================================================");

        return ResponseEntity.ok().body(resp);
    }

    @DeleteMapping("/{noRekamMedis}")
    public ResponseEntity<DeletePasien> deletePasien(HttpServletRequest request, @PathVariable String noRekamMedis) throws JsonProcessingException{
        pasienService.deletePasien(noRekamMedis);
        DeletePasien resp = new DeletePasien(true, "Berhasil menambahkan pasien " + noRekamMedis);
        log.info("===============================================");
        log.info("DELETE " + request.getServletPath());
        log.info("------------------------------------------------");
        log.info("Response");
        log.info(new ObjectMapper().writeValueAsString(resp));
        log.info("------------------------------------------------");
        log.info("================================================");

        return ResponseEntity.ok().body(resp);
    }
}

class GetPostPutPasien extends BaseResponse<Pasien>{
    public GetPostPutPasien(boolean success, String message, SuccessResponse response) {
        super(success, message, response);
    }

}

class GetAllPasien extends BaseResponse<List<Pasien>>{
    public GetAllPasien(boolean success, String message, SuccessResponse response) {
        super(success, message, response);
    }
}

class DeletePasien extends BaseResponse<String>{
    public DeletePasien(boolean success, String message, SuccessResponse response) {
        super(success, message, response);
    }

    public DeletePasien(boolean success, String message) {
        super(success, message);
    }
}