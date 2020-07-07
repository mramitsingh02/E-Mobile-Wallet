package com.tester.spring.rest.webservices.repository.converter;

import com.tester.spring.rest.webservices.dto.MaskString;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class AccountConverter implements AttributeConverter<MaskString, String> {

    @Override
    public String convertToDatabaseColumn(MaskString maskString) {
        return maskString.unMaskString();
    }

    @Override
    public MaskString convertToEntityAttribute(String s) {
        return new MaskString(s);
    }
}
