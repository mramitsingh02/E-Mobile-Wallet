package com.tester.spring.rest.webservices.controller.facory;

import com.tester.spring.rest.webservices.dto.ChangePinDTO;
import com.tester.spring.rest.webservices.utils.PinGenerator;

public class ChangeFactory {
    public static ChangePinDTO newChangePinWithMandatoryField(String msisdn, String oldPin) {
        final String newPin = PinGenerator.newPin();
        return ChangePinDTO.builder()
                .msisdn(msisdn)
                .oldPin(oldPin)
                .newPin(newPin)
                .confirmPin(newPin)
                .build();
    }
}
