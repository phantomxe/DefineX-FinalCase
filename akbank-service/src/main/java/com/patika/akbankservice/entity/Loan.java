package com.patika.akbankservice.entity;

import java.math.BigDecimal;

import com.patika.akbankservice.enums.LoanType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter 
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Entity
@Table(name = "loan")
public class Loan implements Serializable, Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "installment")
    private Integer installment;

    private final String bank = "akbank";

    @Column(name = "interest_rate")
    private Double interestRate;

    @Enumerated(EnumType.STRING)
    @Column(name = "loan_type")
    private LoanType loanType;
}
