package com.patika.kredinbizdeservice.client.dto.request;

import java.math.BigDecimal;

import com.patika.kredinbizdeservice.enums.LoanType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GarantiApplicationRequest {

    private Long userId;
    private BigDecimal amount;
    private Integer installment;
    private LoanType loanType;
}