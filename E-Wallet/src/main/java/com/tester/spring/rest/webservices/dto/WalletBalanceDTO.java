package com.tester.spring.rest.webservices.dto;

import com.tester.spring.rest.webservices.repository.pojo.Amount;
import com.tester.spring.rest.webservices.repository.pojo.TimePeriod;
import com.tester.spring.rest.webservices.repository.pojo.WalletBalances;
import lombok.Data;

import java.io.Serializable;
import java.util.Optional;

@Data
public class WalletBalanceDTO implements Serializable {

    private static final long serialVersionUID = 2330019623894757610L;
    private String walletNumber;
    private Optional<MataInfoDTO> mataInfoDTO;
    private Long walletSequenceNumber = 0l;
    private Amount balance;
    private Amount balanceCredit;
    private Amount balanceDebit;
    private Amount frozenAmount;
    private Amount previousBalance;
    private Amount previousCash;
    private TimePeriod dailyWalletUpdatedOn;
    private String lastTransferBalanceAction;
    private String lastTransferCashAction;
    private String lastTransferId;
    private TimePeriod lastTransferOn;
    private String lastTransferType;
    private Long paymentTypeId;
    private Long providerId;
    private Long reimbCredit = 0l;
    private Long reimbDebit = 0l;


    public WalletBalanceDTO(WalletBalances wb) {
        setWalletNumber(wb.getWalletNumber());
        // setMataInfoDTO(wb.getMataInfo().toDto());
        setWalletSequenceNumber(wb.getWalletSequenceNumber());
        setBalance(wb.getBalance());
        setBalanceCredit(wb.getBalanceCredit());
        setBalanceDebit(wb.getBalanceDebit());
        setFrozenAmount(wb.getFrozenAmount());
        setDailyWalletUpdatedOn(wb.getDailyWalletUpdatedOn());
        setLastTransferBalanceAction(wb.getLastTransferBalanceAction());
        setLastTransferCashAction(wb.getLastTransferCashAction());
        setLastTransferId(wb.getLastTransferId());
        setLastTransferOn(wb.getLastTransferOn());
        setLastTransferType(wb.getLastTransferType());
        setPaymentTypeId(wb.getPaymentTypeId());
        setPreviousBalance(wb.getPreviousBalance());
        setPreviousCash(wb.getPreviousCash());
        setProviderId(wb.getProviderId());
        setReimbCredit(wb.getReimbCredit());
        setReimbDebit(wb.getReimbDebit());
    }
}
