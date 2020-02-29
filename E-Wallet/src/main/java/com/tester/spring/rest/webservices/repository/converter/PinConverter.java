package com.tester.spring.rest.webservices.repository.converter;

import com.tester.spring.rest.webservices.repository.pojo.Pin;
import lombok.NonNull;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class PinConverter implements AttributeConverter<Pin, String> {

    @Override
    public String convertToDatabaseColumn(@NonNull Pin pin) {
        return pin.reverse();
    }

    @Override
    public Pin convertToEntityAttribute(@NonNull String pinValue) {
        return new Pin(pinValue).reversePin();
    }
}
