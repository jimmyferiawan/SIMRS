package com.simrs.jimmy;

import com.simrs.jimmy.dto.request.PetugasRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class CustomValidatorTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }


    @Test
    public void JenisKelaminTypeSubsetValidatorTest() {
        PetugasRequest petugasRequest = new PetugasRequest();
        petugasRequest.setJk("K");
        Set violations = validator.validate(petugasRequest);

        for(Object violation: violations) {
            System.out.println(violation);
        }
    }
}
