package com.tester.spring.rest.webservices.repository.pojo;

import com.tester.spring.rest.webservices.dto.WalletSpecificationDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "e_wallet_specification")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WalletSpecification {
    @Id
    @Column(name = "spec_id", length = 32)
    private String specId;

    @Column(name = "spec_name", length = 64)
    private String specName;

    @Column(name = "grade", length = 32)
    private String walletGrade;

    @Column(name = "ref_spec_id", length = 32)
    private String refSpecId;


    public WalletSpecification(WalletSpecificationDTO walletSpecification) {
        this.specId = walletSpecification.getSpecId();
        this.specName = walletSpecification.getSpecName();
    }


    public WalletSpecificationDTO asDTO() {
        return new WalletSpecificationDTO(specId, specName);
    }

    public WalletSpecification merge(@NonNull WalletSpecificationDTO walletSpecification) {
        specName = walletSpecification.getSpecName();
        return this;
    }
}
