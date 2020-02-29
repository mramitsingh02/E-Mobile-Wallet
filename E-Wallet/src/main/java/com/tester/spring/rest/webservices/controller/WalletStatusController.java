package com.tester.spring.rest.webservices.controller;

import com.tester.spring.rest.webservices.dto.StatusChangeDTO;
import com.tester.spring.rest.webservices.dto.WalletDTO;
import com.tester.spring.rest.webservices.services.WalletServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/walletStatus")
public class WalletStatusController {

    @Autowired
    private WalletServices walletServices;


    @PutMapping(path = "/msisdn/{msisdn}/status/{status}")
    public MappingJacksonValue changeStatus(@PathVariable String msisdn, @PathVariable String status) {
        WalletDTO wallet = walletServices.changeChangeStatus(msisdn, status);
        return wallet.getMappingJacksonValue();
    }


    @PutMapping(path = "/msisdn/{msisdn}")
    public MappingJacksonValue changeStatus(@PathVariable String msisdn, @RequestBody StatusChangeDTO changeStatus) {
        WalletDTO wallet = walletServices.changeChangeStatus(msisdn, changeStatus);
        return wallet.getMappingJacksonValue();
    }


}
