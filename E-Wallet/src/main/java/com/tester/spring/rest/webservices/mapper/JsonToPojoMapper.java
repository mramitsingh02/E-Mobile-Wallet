package com.tester.spring.rest.webservices.mapper;

import com.tester.spring.rest.webservices.dto.WalletDTO;
import com.tester.spring.rest.webservices.repository.pojo.Wallet;

public class JsonToPojoMapper<From, To> {


    public static Wallet WalletDTOToWallet(WalletDTO walletDTO) {
        return new Wallet(walletDTO);
    }

}
