package com.simrs.jimmy.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simrs.jimmy.dto.response.BaseResponse;
import com.simrs.jimmy.dto.request.PetugasRequest;
import com.simrs.jimmy.dto.response.format.SuccessResponse;
import com.simrs.jimmy.entity.constenum.JK;
import com.simrs.jimmy.entity.Petugas;
import com.simrs.jimmy.service.PetugasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/petugas")
public class PetugasController {

    protected BaseResponse respPetugas;
    @Autowired
    private PetugasService petugasService;

    @PostMapping(produces = "application/json")
    public ResponseEntity<BaseResponse> savePetugas(@RequestBody @Valid PetugasRequest petugasRequest) {
        System.out.println("savePetugas()->petugasRequest");
        try {
            System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(petugasRequest));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Petugas petugas = petugasService.createPetugas(new Petugas(petugasRequest.getNip(), petugasRequest.getNama(), JK.valueOf(petugasRequest.getJk()), petugasRequest.getTmp_lahir(), petugasRequest.getTgl_lahir(), petugasRequest.getAlamat(), petugasRequest.getPassword()));
        respPetugas = new BaseResponse();
        SuccessResponse<Petugas> petugasRequestSuccessResponse = new SuccessResponse<>();
        petugasRequestSuccessResponse.setData(petugas);
        respPetugas.setResponse(petugasRequestSuccessResponse);
        respPetugas.setSuccess(true);
        respPetugas.setMessage("berhasil post data");

        return new ResponseEntity<>(respPetugas, HttpStatus.CREATED);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<BaseResponse> getAllPetugas() {
        respPetugas = new BaseResponse();
        List<Petugas> allPetugas;
        SuccessResponse<List<Petugas>> petugasListAll = new SuccessResponse<>();
        HttpStatus status;
        try {
            allPetugas = petugasService.readAllPetugas();
            petugasListAll.setData(allPetugas);
            respPetugas.setResponse(SuccessResponse.build(allPetugas));
            respPetugas.setMessage("Berhasil get data Petugas");
            respPetugas.setSuccess(true);
            status = HttpStatus.OK;
        } catch (Exception ex) {
            respPetugas.setSuccess(false);
            respPetugas.setMessage("Internal server error");
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(respPetugas, status);
    }

    @GetMapping(path = "/{nip}", produces = "application/json")
    public ResponseEntity<BaseResponse> getPetugas(@PathVariable String nip) {
        nip = nip.trim();
        Petugas petugas;
        petugas = petugasService.readPetugasById(nip);
        SuccessResponse<Petugas> successResponse = SuccessResponse.build(petugas);
        BaseResponse resp = new BaseResponse(true, "berhasil get data", successResponse);

        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @PutMapping(path = "/{nip}")
    public ResponseEntity<BaseResponse> updatePetugas(@RequestBody PetugasRequest petugasRequest, @PathVariable String nip) {
        nip = nip.trim();
        Petugas petugas = new Petugas(nip, petugasRequest.getNama(), petugasRequest.getJk(), petugasRequest.getTmp_lahir(), petugasRequest.getTgl_lahir(), petugasRequest.getAlamat(), petugasRequest.getPassword());
        petugasService.updatePetugas(petugas, nip);
        BaseResponse resp = new BaseResponse(true, "berhasil update data", SuccessResponse.build(petugas));

        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{nip}")
    public ResponseEntity<BaseResponse> deletePetugas(@PathVariable String nip) {
        nip = nip.trim();
        petugasService.deletePetugas(nip);

        return ResponseEntity.ok().body(new BaseResponse(true, "Berhasil hapus data"));
    }
//    @GetMapping(path = "/tes/{nip}", produces = "application/json")
//    public ResponseEntity<BaseResponse> readCoba(@PathVariable String nip) {
//        nip = nip.trim();
//
//        Petugas petugas = petugasService.read(nip);
//        SuccessResponse<Petugas> successResponse = new SuccessResponse<Petugas>();
//        successResponse.setData(petugas);
//        BaseResponse resp = new BaseResponse();
//        resp.setSuccess(true);
//        resp.setMessage("Berhasil get mendapatkan data");
//        resp.setResponse(SuccessResponse.build(petugas));
//
//        return new ResponseEntity<>(resp, HttpStatus.OK);
//    }
//
//    @PostMapping(path = "/tes", produces = "application/json")
//    public String createCoba() {
//        petugasService.create();
//        return "createCoba()";
//    }
}
