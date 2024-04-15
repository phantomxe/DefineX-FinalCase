package com.patika.akbankservice.dto.request;

import java.math.BigDecimal;

import com.patika.akbankservice.enums.LoanType;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ApplicationRequest {

    private Long userId;
    private BigDecimal amount;
    private Integer installment;
    private LoanType loanType;

}
