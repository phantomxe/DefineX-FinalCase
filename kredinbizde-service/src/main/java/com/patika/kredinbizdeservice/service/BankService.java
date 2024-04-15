package com.patika.kredinbizdeservice.service;
 
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.patika.kredinbizdeservice.client.AkbankServiceClient;
import com.patika.kredinbizdeservice.client.GarantiServiceClient;
import com.patika.kredinbizdeservice.client.dto.response.CreditCardResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service 
@RequiredArgsConstructor
@Slf4j
public class BankService {

    private final AkbankServiceClient akbankServiceClient;
    private final GarantiServiceClient garantiServiceClient;
    
    public List<CreditCardResponse> getCreditCards() {
        List<CreditCardResponse> akbankCreditCards = akbankServiceClient.getCreditCards();
        List<CreditCardResponse> garantiCreditCards = garantiServiceClient.getCreditCards();
        
        return Stream.concat(akbankCreditCards.stream(), garantiCreditCards.stream()).toList();
    }
}
