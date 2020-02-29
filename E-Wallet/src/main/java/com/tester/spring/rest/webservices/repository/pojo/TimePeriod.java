package com.tester.spring.rest.webservices.repository.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimePeriod {
    private LocalDateTime time;


}
