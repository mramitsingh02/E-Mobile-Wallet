package com.tester.spring.rest.webservices.controller.facory;

import com.tester.spring.rest.webservices.dto.StatusChangeDTO;
import com.tester.spring.rest.webservices.dto.StatusDTO;
import com.tester.spring.rest.webservices.repository.pojo.ValidFor;
import com.tester.spring.rest.webservices.repository.type.StatusType;

public class StatusChangeFactory {
    public static StatusChangeDTO newStatusChangeWithMandatoryField(String msisdn, StatusType available) {
        return new StatusChangeDTO(null, msisdn, new StatusDTO(available, ValidFor.create()));
    }
}
