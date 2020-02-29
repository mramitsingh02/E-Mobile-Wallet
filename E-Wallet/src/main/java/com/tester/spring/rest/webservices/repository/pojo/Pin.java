package com.tester.spring.rest.webservices.repository.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public final class Pin {
    private final String value;

    public String reverse() {
        return new StringBuffer(value).reverse().toString();
    }

    public Pin reversePin() {
        return new Pin(this.reverse());
    }


}
