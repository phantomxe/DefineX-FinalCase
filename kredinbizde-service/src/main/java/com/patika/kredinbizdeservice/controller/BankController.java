package com.patika.kredinbizdeservice.controller;
 
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patika.kredinbizdeservice.client.dto.response.CreditCardResponse;
import com.patika.kredinbizdeservice.service.BankService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/banks")
@RequiredArgsConstructor
public class BankController {

    private final BankService bankService;
    
    @GetMapping("/creditcards")
    public List<CreditCardResponse> getCreditCards() {
        return bankService.getCreditCards();
    }
}
