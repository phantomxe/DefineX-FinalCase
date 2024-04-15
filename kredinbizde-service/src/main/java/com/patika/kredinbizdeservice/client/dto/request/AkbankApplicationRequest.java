package com.patika.kredinbizdeservice.client.dto.request;

import java.math.BigDecimal;

import com.patika.kredinbizdeservice.enums.LoanType;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AkbankApplicationRequest {

    private Long userId;
    private BigDecimal amount;
    private Integer installment;
    private LoanType loanType;
}