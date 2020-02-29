package com.tester.spring.rest.webservices.repository.pojo;

import com.tester.spring.rest.webservices.repository.converter.TimePeriodConverter;
import com.tester.spring.rest.webservices.repository.factory.TimePeriodFactory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidFor {
    @Column(name = "start", updatable = false)
    @Convert(converter = TimePeriodConverter.class)
    private TimePeriod start;

    @Column(name = "end")
    @Convert(converter = TimePeriodConverter.class)
    private TimePeriod end;

    public static ValidFor create() {
        return new ValidFor(TimePeriodFactory.now(), null);
    }

    public static ValidFor infinity() {
        return new ValidFor(TimePeriodFactory.now(), null);
    }

}
