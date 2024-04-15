package com.patika.akbankservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.patika.akbankservice.dto.request.CreditCardCreateRequest;
import com.patika.akbankservice.dto.response.CreditCardResponse;
import com.patika.akbankservice.entity.Campaign;
import com.patika.akbankservice.entity.CreditCard;
import com.patika.akbankservice.repository.CampaignRepository;
import com.patika.akbankservice.repository.CreditCardRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreditCardService {
    
    public final CreditCardRepository creditCardRepository;
    public final CampaignRepository campaignRepository;

    public List<CreditCard> getAll() {
        return creditCardRepository.findAll();
    }

    public CreditCardResponse createCreditCard(CreditCardCreateRequest request) {
        List<Campaign> saved_campaigns = campaignRepository.saveAll(
            request.getCampaignList()
            .stream()
            .map(inner -> Campaign.builder()
                    .title(inner.getTitle())
                    .content(inner.getContent())
                    .dueDate(inner.getDueDate())
                    .createDate(inner.getCreateDate())
                    .updateDate(inner.getUpdateDate())
                    .sector(inner.getSector())
                    .build()) 
            .toList());


        CreditCard creditCard = creditCardRepository.save(
            CreditCard.builder()
            .name(request.getName())
            .fee(request.getFee())
            .campaignList(saved_campaigns)
            .build());

        log.info("Credit Card created: {}", creditCard.toString());

        return new CreditCardResponse(creditCard.getId());
    }
}
