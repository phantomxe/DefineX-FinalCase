package com.patika.akbankservice.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
 
import com.patika.akbankservice.entity.Campaign;
import com.patika.akbankservice.repository.CampaignRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CampaignService {
    
    private final CampaignRepository campaignRepository;

    public Campaign getById(Long id) {
        Optional<Campaign> campaign =  campaignRepository.findById(id);
        return campaign.orElse(null);
    }
}
