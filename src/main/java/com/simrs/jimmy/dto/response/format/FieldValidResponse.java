package com.simrs.jimmy.dto.response.format;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "build")
public class FieldValidResponse {
    private String field;
    private String message;
}
