package com.tester.spring.rest.webservices.dto;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.tester.spring.rest.webservices.mapper.MappingJacksonUtils;
import com.tester.spring.rest.webservices.repository.pojo.Amount;
import com.tester.spring.rest.webservices.repository.pojo.Wallet;
import com.tester.spring.rest.webservices.utils.PinGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.converter.json.MappingJacksonValue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.tester.spring.rest.webservices.controller.RestConstants.WALLET_DETAILS_FILTER;
import static com.tester.spring.rest.webservices.controller.RestConstants.WALLET_FIELDS;

@Data
@JsonFilter("WalletDetailsFilter")
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WalletDTO {

    private String walletNumber;
    private String bankAccountNumber;
    private String msisdn;
    private String pin;
    private Amount amount;
    private boolean isPinChanged;
    private String walletSpecId;
    private String provider;
    private String paymentType;
    private LocalDateTime createdTime;
    private LocalDateTime updateTime;
    private List<StatusDTO> statuses;

    public WalletDTO(Wallet wallet) {
        setPaymentType(wallet.getPaymentType());
        setProvider(wallet.getProvider());
        setMsisdn(wallet.getMsisdn());
        setPin(wallet.getPin().getValue());
        setAmount(wallet.getAmount());
        setBankAccountNumber(wallet.getBankAccountNumber());
        setWalletNumber(wallet.getWalletNumber());
        setPinChanged(wallet.isPinChanged());
        setWalletSpecId(wallet.getWalletSpecId());
        setCreatedTime(wallet.getCreateUpdateDateTime().getCreateTime());
        setStatuses(wallet.getStatuses().stream().map(StatusDTO::new).collect(Collectors.toList()));
    }

    public void setRandom() {
        setPin(PinGenerator.newPin());
    }

    public void setSupplierWalletNumber(Supplier<String> walletNumber) {
        this.walletNumber = walletNumber.get();
    }

    public Wallet asPojo() {
        return new Wallet(this);
    }

    public MappingJacksonValue getMappingJacksonValue() {
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(this);
        MappingJacksonUtils.setFilterInMapping(WALLET_DETAILS_FILTER, mappingJacksonValue, WALLET_FIELDS);
        return mappingJacksonValue;
    }


}
