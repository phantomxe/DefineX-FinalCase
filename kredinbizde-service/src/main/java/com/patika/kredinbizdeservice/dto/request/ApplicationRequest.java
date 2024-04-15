package com.patika.kredinbizdeservice.dto.request;

import java.math.BigDecimal;

import com.patika.kredinbizdeservice.enums.LoanType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationRequest {

    private String email;
    private BigDecimal amount;
    private Integer installment;
    private LoanType loanType;

}
