package com.patika.garantiservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patika.garantiservice.entity.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long>{
    
}
