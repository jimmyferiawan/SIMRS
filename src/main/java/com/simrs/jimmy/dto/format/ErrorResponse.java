package com.simrs.jimmy.dto.format;

import lombok.Data;

@Data
public class ErrorResponse<T> {
    private T details;
}
