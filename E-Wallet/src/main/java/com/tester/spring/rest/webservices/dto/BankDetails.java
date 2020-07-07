package com.tester.spring.rest.webservices.dto;

import com.tester.spring.rest.webservices.repository.pojo.LinkedBanks;
import com.tester.spring.rest.webservices.repository.pojo.Pin;
import lombok.Data;

@Data
public class BankDetails {
    private static final long serialVersionUID = 2330019623894757610L;
    private String bankCode;
    private String branchCode;
    private String ifscCode;
    private String bankAccount;
    private String accountHolderName;
    private String accessPin;
    private String status;

    private final LinkedBanks linkedBanks;

    public BankDetails(LinkedBanks linkedBanks) {
        this.linkedBanks = linkedBanks;
    }
}
