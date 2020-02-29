package com.tester.spring.rest.webservices.dto;

import com.tester.spring.rest.webservices.repository.pojo.Status;
import com.tester.spring.rest.webservices.repository.pojo.ValidFor;
import com.tester.spring.rest.webservices.repository.type.StatusType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class StatusDTO {
    private long statusId;
    private StatusType value;
    private ValidFor validFor;
    private String walletNumber;

    public StatusDTO(StatusType value, ValidFor validFor) {
        this.value = value;
        this.validFor = new ValidFor(validFor.getStart(), validFor.getEnd());
    }

    public StatusDTO(Status status) {
        walletNumber = status.getWallet().getWalletNumber();
        value = status.getValue();
        statusId = status.getStatusId();
        validFor = new ValidFor(status.getValidFor().getStart(), status.getValidFor().getEnd());
    }

    public boolean isStatusSame(StatusType statusType) {
        return value.equals(statusType);
    }

    public Status asPojo() {
        return new Status(this);
    }
}
