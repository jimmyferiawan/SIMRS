package com.simrs.jimmy.dto.format;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BaseResponse {
    private boolean success;
    private String message;
    private SuccessResponse response;
    private ErrorResponse errors;


    public BaseResponse(boolean success, String message, SuccessResponse response) {
        this.success = success;
        this.message = message;
        this.response = response;
    }

    public BaseResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public BaseResponse(SuccessResponse response) {
        this.response = response;
    }
}
