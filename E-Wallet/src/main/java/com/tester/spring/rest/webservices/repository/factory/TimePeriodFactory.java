package com.tester.spring.rest.webservices.repository.factory;

import com.tester.spring.rest.webservices.repository.pojo.TimePeriod;

import java.time.LocalDateTime;


public class TimePeriodFactory {

    public static TimePeriod now() {
        return new TimePeriod(LocalDateTime.now());
    }

}
