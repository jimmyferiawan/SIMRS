package com.simrs.jimmy.advice;

import com.simrs.jimmy.dto.format.BaseResponse;
import com.simrs.jimmy.dto.format.ErrorResponse;
import com.simrs.jimmy.dto.format.FieldValidResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

//@RestControllerAdvice
//public class CustomValidationExceptionHandler extends ResponseEntityExceptionHandler {
//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        System.out.println("status.name(): "+ status.name() + ", status.value(): " + status.value());
//        List<FieldValidResponse> validationErrors = new ArrayList<>();
//        ex.getBindingResult().getFieldErrors().forEach(error -> {
//            validationErrors.add(FieldValidResponse.build(error.getField(), error.getDefaultMessage()));
//        });
//
//        ErrorResponse errorResponse = new ErrorResponse();
//        errorResponse.setDetails(validationErrors);
//
//        BaseResponse resp = new BaseResponse();
//        resp.setSuccess(false);
//        resp.setMessage("Validation error");
//        resp.setErrors(errorResponse);
//
//
//        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
//    }
//
//
//}

//@RestControllerAdvice
//public class CustomValidationExceptionHandler extends ResponseEntityExceptionHandler {
//    @Override
//    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
////        return super.handleHttpMessageNotReadable(ex, headers, status, request);
//    }
//}