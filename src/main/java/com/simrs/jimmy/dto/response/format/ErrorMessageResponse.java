package com.simrs.jimmy.dto.response.format;

public class ErrorMessageResponse {
    private String message;

    public ErrorMessageResponse() {
    }

    public ErrorMessageResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
