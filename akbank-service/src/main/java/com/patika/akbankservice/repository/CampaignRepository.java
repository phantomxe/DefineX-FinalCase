package com.patika.akbankservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patika.akbankservice.entity.Campaign;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {
    
}
