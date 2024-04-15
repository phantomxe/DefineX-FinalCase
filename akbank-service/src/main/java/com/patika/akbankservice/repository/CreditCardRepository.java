package com.patika.akbankservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patika.akbankservice.entity.CreditCard;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
    
}
