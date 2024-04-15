package com.patika.akbankservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patika.akbankservice.dto.request.CreditCardCreateRequest;
import com.patika.akbankservice.dto.response.CreditCardResponse;
import com.patika.akbankservice.entity.CreditCard;
import com.patika.akbankservice.service.CreditCardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/akbank/v1/cards")
@RequiredArgsConstructor
public class CreditCardController {
    
    public final CreditCardService creditCardService;

    @GetMapping
    public List<CreditCard> getAll() {
        return creditCardService.getAll();
    }

    @PostMapping
    public CreditCardResponse createCreditCard(@RequestBody CreditCardCreateRequest request) {
        return creditCardService.createCreditCard(request);
    }
}
