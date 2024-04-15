package com.patika.akbankservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patika.akbankservice.entity.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long>{
    
}
