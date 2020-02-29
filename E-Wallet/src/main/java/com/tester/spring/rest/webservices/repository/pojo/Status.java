package com.tester.spring.rest.webservices.repository.pojo;

import com.tester.spring.rest.webservices.dto.StatusDTO;
import com.tester.spring.rest.webservices.repository.type.StatusType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "status")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Status {
    @Id
    @GeneratedValue
    @Column(name = "status_id")
    private long statusId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wallet_number")
    private Wallet wallet;

    @Column(name = "status")
    private StatusType value;
    @Embedded
    private ValidFor validFor;

    public Status(StatusDTO statusDTO) {
        wallet = Wallet.ofWalletNumber(statusDTO.getWalletNumber());
        value = statusDTO.getValue();
        validFor = new ValidFor(statusDTO.getValidFor().getStart(), statusDTO.getValidFor().getEnd());
    }

    public StatusDTO asDTO(Status status) {
        return new StatusDTO(this);
    }
}
