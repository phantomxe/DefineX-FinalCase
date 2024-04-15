package com.patika.garantiservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patika.garantiservice.entity.Campaign;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {
    
}

