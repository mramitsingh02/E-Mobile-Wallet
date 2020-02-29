package com.tester.spring.rest.webservices.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StatusChangeDTO {
    private String walletNumber;
    private String msisdn;
    private StatusDTO statuses;
}
