package com.tester.spring.rest.webservices.repository.pojo;

import com.tester.spring.rest.webservices.dto.BankDetails;
import com.tester.spring.rest.webservices.repository.converter.PinConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class LinkedBanks {
    @Id
    @GeneratedValue
    @Column(name = "link_bank_id")
    private long linkBankId;
    @Column(name = "BANK_CODE", unique = true, insertable = false, updatable = true)
    private String bankCode;
    @Column(name = "BRANCH_CODE")
    private String branchCode;
    @Column(name = "IFSC_CODE")
    private String ifscCode;
    @Column(name = "BANK_ACCOUNT")
    private String bankAccount;
    @Column(name = "HOLDER_NAME")
    private String accountHolderName;
    @Convert(converter = PinConverter.class)
    @Column(name = "ACCESS_PIN")
    private Pin accessPin;
    @Column(name = "STATUS")
    private String status;
    @ManyToOne
    private Wallet wallet;

    public LinkedBanks(BankDetails bankDetails) {
        this.bankCode = bankDetails.getBankCode();
        this.branchCode = bankDetails.getBranchCode();
        this.ifscCode = bankDetails.getIfscCode();
        this.bankAccount = bankDetails.getBankAccount();
        this.accountHolderName = bankDetails.getAccountHolderName();
        this.accessPin = new Pin(bankDetails.getAccessPin());
        this.status = bankDetails.getStatus();
    }
}
