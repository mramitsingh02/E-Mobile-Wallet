package com.tester.spring.rest.webservices.dto;

import com.tester.spring.rest.webservices.repository.pojo.WalletSpecification;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.function.Supplier;

@Data
@Builder
@AllArgsConstructor
@ToString
public class WalletSpecificationDTO {
    private String specId;

    private String specName;

    public WalletSpecificationDTO(WalletSpecification walletSpecification) {
        this.specId = walletSpecification.getSpecId();
        this.specName = walletSpecification.getSpecName();
    }

    public void setSupplierSpecId(Supplier<String> specId) {
        this.specId = specId.get();
    }

    public WalletSpecification asPojo() {
        return new WalletSpecification(this);
    }
}
