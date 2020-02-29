package com.tester.spring.rest.webservices.repository.type;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public enum StatusType {
    AVAILABLE("Available"), ACTIVE("Active"), DEACTIVATE("DeActivate"), SUSPENDED("Suspended"), DELETED("deleted");
    private String value;

}
