package com.tester.spring.rest.webservices.mapper;

import com.tester.spring.rest.webservices.dto.WalletDTO;
import com.tester.spring.rest.webservices.repository.pojo.Wallet;

public class PojoToJsonMapper {

    public static WalletDTO WalletToWalletDTO(Wallet wallet) {
        return new WalletDTO(wallet);
    }

}
