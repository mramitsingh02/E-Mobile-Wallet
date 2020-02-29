package com.tester.spring.rest.webservices.repository.converter;

import com.tester.spring.rest.webservices.repository.pojo.Amount;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import static java.util.Objects.*;

@Converter
public class AmountConverter implements AttributeConverter<Amount, Double> {
    @Override
    public Double convertToDatabaseColumn(Amount amount) {
        return isNull(amount) ? 0 : amount.doubleValue();
    }

    @Override
    public Amount convertToEntityAttribute(Double aDouble) {
        return Amount.of(aDouble);
    }
}
