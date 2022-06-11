package com.simrs.jimmy.entity.constenum;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class GolonganDarahConverter implements AttributeConverter<GolonganDarah, String> {

    @Override
    public String convertToDatabaseColumn(GolonganDarah golonganDarah) {
        if(golonganDarah == null) return null;
        return golonganDarah.getValue();
    }

    @Override
    public GolonganDarah convertToEntityAttribute(String s) {
        if(s == null) return null;

        return Stream.of(GolonganDarah.values())
                .filter(v -> v.getValue().equals(s))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
