package com.tester.spring.rest.webservices.controller.facory;

import com.tester.spring.rest.webservices.dto.StatusDTO;
import com.tester.spring.rest.webservices.dto.WalletDTO;
import com.tester.spring.rest.webservices.repository.pojo.Amount;
import com.tester.spring.rest.webservices.repository.pojo.ValidFor;
import com.tester.spring.rest.webservices.repository.type.StatusType;
import com.tester.spring.rest.webservices.utils.LocalIdGenerator;

import java.time.LocalDateTime;
import java.util.Collections;

public class WalletFactory {
    public static WalletDTO newWalletWithMandatoryField() {
        return WalletDTO.builder().msisdn("12345678").pin("1234").build();
    }

    public static WalletDTO newWalletWithAllField() {
        return WalletDTO.builder()
                .msisdn("12345678")
                .pin("1234")
                .amount(Amount.of(12))
                .createdTime(LocalDateTime.now())
                .isPinChanged(true)
                .walletSpecId(LocalIdGenerator.getNewId())
                .statuses(Collections.singletonList(new StatusDTO(StatusType.AVAILABLE, ValidFor.create())))
                .build();
    }
}
