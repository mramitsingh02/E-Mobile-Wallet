package com.tester.spring.rest.webservices.repository.pojo;

import com.tester.spring.rest.webservices.dto.WalletBalanceDTO;
import com.tester.spring.rest.webservices.repository.converter.TimePeriodConverter;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@NoArgsConstructor
@ToString
@Entity(name = "e_wallet_balance")
public class WalletBalances implements Serializable {

    private static final long serialVersionUID = 2330019623894757610L;

    @Id
    @Column(name = "WALLET_NUMBER", length = 21)
    private String walletNumber;
   /* @Embedded
    private MataInfo mataInfo;*/

    @Column(name = "WALLET_SEQUENCE_NUMBER")
    private Long walletSequenceNumber;

    @Column(name = "BALANCE")
    private Amount balance;

    @Column(name = "BALANCE_CREDIT")
    private Amount balanceCredit;

    @Column(name = "BALANCE_DEBIT")
    private Amount balanceDebit;

    @Column(name = "FROZEN_AMOUNT")
    private Amount frozenAmount;

    @Column(name = "DAILY_WALLET_UPDATED_ON")
    @Convert(converter = TimePeriodConverter.class)
    private TimePeriod dailyWalletUpdatedOn;

    @Column(name = "LAST_TRANSFER_BALANCE_ACTION")
    private String lastTransferBalanceAction;

    @Column(name = "LAST_TRANSFER_CASH_ACTION")
    private String lastTransferCashAction;

    @Column(name = "LAST_TRANSFER_ID")
    private String lastTransferId;

    @Column(name = "LAST_TRANSFER_ON")
    @Convert(converter = TimePeriodConverter.class)
    private TimePeriod lastTransferOn;

    @Column(name = "LAST_TRANSFER_TYPE")
    private String lastTransferType;

    @Column(name = "PAYMENT_TYPE_ID")
    private Long paymentTypeId;

    @Column(name = "PREVIOUS_BALANCE")
    private Amount previousBalance;

    @Column(name = "PREVIOUS_CASH")
    private Amount previousCash;

    @Column(name = "PROVIDER_ID")
    private Long providerId;

    @Column(name = "REIMB_CREDIT")
    private Long reimbCredit;

    @Column(name = "REIMB_DEBIT")
    private Long reimbDebit;

    public WalletBalances(WalletBalanceDTO dto) {
        setWalletNumber(dto.getWalletNumber());
        // setMataInfo(dto.getMataInfoDTO().isPresent() ? dto.getMataInfoDTO().get().toPojo() : null);
        setWalletSequenceNumber(dto.getWalletSequenceNumber());
        setBalance(dto.getBalance());
        setBalanceCredit(dto.getBalanceCredit());
        setBalanceDebit(dto.getBalanceDebit());
        setFrozenAmount(dto.getFrozenAmount());
        setDailyWalletUpdatedOn(dto.getDailyWalletUpdatedOn());
        setLastTransferBalanceAction(dto.getLastTransferBalanceAction());
        setLastTransferCashAction(dto.getLastTransferCashAction());
        setLastTransferId(dto.getLastTransferId());
        setLastTransferOn(dto.getLastTransferOn());
        setLastTransferType(dto.getLastTransferType());
        setPaymentTypeId(dto.getPaymentTypeId());
        setPreviousBalance(dto.getPreviousBalance());
        setPreviousCash(dto.getPreviousCash());
        setProviderId(dto.getProviderId());
        setReimbCredit(dto.getReimbCredit());
        setReimbDebit(dto.getReimbDebit());
    }
}
