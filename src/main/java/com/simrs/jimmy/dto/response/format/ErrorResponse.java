package com.simrs.jimmy.dto.response.format;

import lombok.Data;

@Data
public class ErrorResponse<T> {
    private T details;
}
