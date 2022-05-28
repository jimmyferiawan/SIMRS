package com.simrs.jimmy.advice;

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