package com.tester.spring.rest.webservices.repository.converter;

import com.tester.spring.rest.webservices.repository.pojo.TimePeriod;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;

@Converter(autoApply = true)
public class TimePeriodConverter implements AttributeConverter<TimePeriod, Timestamp> {
    @Override
    public Timestamp convertToDatabaseColumn(TimePeriod timePeriod) {
        if (timePeriod == null) {
            return null;
        }
        return Timestamp.valueOf(timePeriod.getTime());
    }

    @Override
    public TimePeriod convertToEntityAttribute(Timestamp sqlDate) {
        if (sqlDate == null) {
            return null;
        }
        return new TimePeriod(sqlDate.toLocalDateTime());
    }
}
