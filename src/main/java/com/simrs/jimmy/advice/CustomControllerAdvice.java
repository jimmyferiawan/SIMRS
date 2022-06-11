package com.simrs.jimmy.advice;

import com.simrs.jimmy.dto.response.BaseResponse;
import com.simrs.jimmy.dto.response.format.ErrorResponse;
import com.simrs.jimmy.dto.response.format.FieldValidResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@RestController
@Slf4j
public class CustomControllerAdvice {

    private BaseResponse baseResponse;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleJsonFormat(HttpMessageNotReadableException ex) {
        log.info("handleJsonFormat(): error format JSON");
        baseResponse = new BaseResponse();
        baseResponse.setMessage("error format JSON");
        return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse> customHandleValidation(MethodArgumentNotValidException ex) {
        log.info("Validation request error");
//        System.out.println("status.name(): "+ status.name() + ", status.value(): " + status.value());
        List<FieldValidResponse> validationErrors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            validationErrors.add(FieldValidResponse.build(error.getField(), error.getDefaultMessage()));
        });
        baseResponse = new BaseResponse();
        ErrorResponse<List<FieldValidResponse>> errorResponse = new ErrorResponse();
        errorResponse.setDetails(validationErrors);
        baseResponse.setSuccess(false);
        baseResponse.setMessage("Validation error");
        baseResponse.setErrors(errorResponse);

        return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);

    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<BaseResponse> customEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        log.info("EntityNotFound");
        baseResponse = new BaseResponse(false, "Data dengan id " + ex.getMessage() + " tidak ditemukan");

        return new ResponseEntity<>(baseResponse, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<BaseResponse> customEntityExistsException(EntityExistsException ex, WebRequest request) {
        log.info("EntityExists");
        baseResponse = new BaseResponse(false, "Data dengan id " + ex.getMessage() + " sudah ada");

        return new ResponseEntity<>(baseResponse, HttpStatus.CONFLICT);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<BaseResponse> customSqlIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex, WebRequest request) {
        log.info("SQLIntegrityConstraintViolationException.class");
        baseResponse = new BaseResponse(false, "Data dengan id " + ex.getMessage() + " sudah ada");

        return new ResponseEntity<>(baseResponse, HttpStatus.CONFLICT);
    }
}
