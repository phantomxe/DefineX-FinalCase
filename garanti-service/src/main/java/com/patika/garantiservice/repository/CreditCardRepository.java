package com.patika.garantiservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patika.garantiservice.entity.CreditCard;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
    
}
