package com.tester.spring.rest.webservices.repository.pojo;

import com.tester.spring.rest.webservices.dto.BankDetails;
import com.tester.spring.rest.webservices.dto.WalletDTO;
import com.tester.spring.rest.webservices.repository.converter.AmountConverter;
import com.tester.spring.rest.webservices.repository.converter.PinConverter;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Objects.*;

@Entity(name = "e_wallet")
@Data
@NoArgsConstructor
public class Wallet {
    @Id
    @Column(name = "wallet_number", updatable = false, length = 64)
    private String walletNumber;
    @Column(name = "msisdn", nullable = false, length = 32)
    private String msisdn;
    @Convert(converter = PinConverter.class)
    private Pin pin;
    @Convert(converter = AmountConverter.class)
    @Column(name = "amount")
    private Amount amount;
    @Column(name = "is_pin_changed")
    private boolean isPinChanged;
    @Column(name = "wallet_spec_id", nullable = false, length = 64)
    private String walletSpecId;
    @Embedded
    private CreateUpdateDateTime createUpdateDateTime;
    @Column(name = "provider", length = 6)
    private String provider;
    @Column(name = "payment_type", length = 6)
    private String paymentType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "wallet", fetch = FetchType.EAGER)
    private List<Status> statuses;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "wallet", fetch = FetchType.EAGER)
    @Column(name = "link_bank_id")
    private List<LinkedBanks> linkedBanks;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "USER_WALLET_BALANCE_MAP", joinColumns = {@JoinColumn(name = "WALLET_NUMBER")}, inverseJoinColumns = @JoinColumn(name = "WALLET_NUMBER"))
    private List<WalletBalances> walletBalances;

    public Wallet(WalletDTO walletDTO) {
        setPaymentType(walletDTO.getPaymentType());
        setProvider(walletDTO.getProvider());
        setMsisdn(walletDTO.getMsisdn());
        setPin(new Pin(walletDTO.getPin()));
        setAmountWithDefaultValue(walletDTO);
        populateLinkedBanks(walletDTO.getBankDetails());
        setWalletNumber(walletDTO.getWalletNumber());
        setPinChanged(walletDTO.isPinChanged());
        setWalletSpecId(walletDTO.getWalletSpecId());
        updateTime(walletDTO);
        setStatuses(walletDTO.getStatuses().stream().map(Status::new).collect(Collectors.toList()));

    }

    public static Wallet ofWalletNumber(String walletNumber) {
        final Wallet wallet = new Wallet();
        wallet.setWalletNumber(walletNumber);
        return wallet;
    }

    public static Wallet of(String msisdn) {
        final Wallet wallet = new Wallet();
        wallet.setMsisdn(msisdn);
        return wallet;
    }

    public static Wallet of(String msisdn, String pin) {
        final Wallet wallet = of(msisdn);
        wallet.setPin(new Pin(pin));
        return wallet;
    }

    public static CreateUpdateDateTime createOf() {
        return new CreateUpdateDateTime(LocalDateTime.now(), null, null);
    }

    public static LocalDateTime now() {
        return LocalDateTime.now();
    }

    public static CreateUpdateDateTime updateOf(CreateUpdateDateTime createUpdateDateTime) {
        createUpdateDateTime.setUpdateTime(now());
        return createUpdateDateTime;
    }

    public static CreateUpdateDateTime deleteOf(CreateUpdateDateTime createUpdateDateTime) {
        createUpdateDateTime.setDeleteTime(now());
        return createUpdateDateTime;
    }

    private void populateLinkedBanks(List<BankDetails> bankDetails) {
        if (isNull(bankDetails)) return;

        setLinkedBanks(bankDetails.stream().map(LinkedBanks::new).collect(Collectors.toList()));

    }

    private void updateTime(WalletDTO walletDTO) {

        if (isNull(this.createUpdateDateTime)) setCreateUpdateDateTime(createOf(walletDTO.getCreatedTime()));
        else setCreateUpdateDateTime(updateOf(this.createUpdateDateTime));
    }

    public boolean isPinExist() {
        return Objects.nonNull(this.pin);
    }

    private void setAmountWithDefaultValue(WalletDTO walletDTO) {
        if (isNull(walletDTO.getAmount())) setAmount(Amount.of());
        else setAmount(walletDTO.getAmount());
        walletDTO.setAmount(getAmount());
    }

    public WalletDTO asDTO() {
        return new WalletDTO(this);
    }

    private CreateUpdateDateTime createOf(LocalDateTime createdTime) {
        return new CreateUpdateDateTime(createdTime, null, null);
    }
}
