package com.patika.garantiservice.entity;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoanLimit {
    private BigDecimal maxAmount;
    private BigDecimal minAmount;
    private Double interestRate;
    private Integer installment;
}
