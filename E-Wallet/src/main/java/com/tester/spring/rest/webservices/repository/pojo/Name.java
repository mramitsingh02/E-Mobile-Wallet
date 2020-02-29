package com.tester.spring.rest.webservices.repository.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public final class Name {
    private String firstName;
    private String middleName;
    private String lastName;
}
