package com.tester.spring.rest.webservices.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChangePinDTO {
    private String walletNumber;
    private String msisdn;
    private String oldPin;
    private String newPin;
    private String confirmPin;
}
