package com.patika.garantiservice.dto.request;

import java.math.BigDecimal;

import com.patika.garantiservice.enums.LoanType;

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
