package com.patika.kredinbizdeservice.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.patika.kredinbizdeservice.client.AkbankServiceClient;
import com.patika.kredinbizdeservice.client.GarantiServiceClient;
import com.patika.kredinbizdeservice.client.dto.request.GarantiApplicationRequest;
import com.patika.kredinbizdeservice.client.dto.response.CreditCardResponse;
import com.patika.kredinbizdeservice.enums.SectorType;
import com.patika.kredinbizdeservice.model.Campaign;
 

@ExtendWith(MockitoExtension.class)
public class BankServiceTest {
    
    @InjectMocks
    private BankService bankService;

    @Mock
    private AkbankServiceClient akbankServiceClient;

    @Mock
    private GarantiServiceClient garantiServiceClient;

    @Test
    public void should_get_credit_cards() { 
        Mockito.when(akbankServiceClient.getCreditCards()).thenReturn(prepareAkbankCreditCards());
        Mockito.when(garantiServiceClient.getCreditCards()).thenReturn(prepareGarantiCreditCards());
        
        // when 
        List<CreditCardResponse> creditCardResponseList = bankService.getCreditCards();

        // then
        System.out.println(creditCardResponseList.toString());

        assertThat(creditCardResponseList).isNotNull();
        assertThat(creditCardResponseList.size()).isEqualTo(2); 
        assertThat(creditCardResponseList.stream().filter(resp -> resp.getName().equals("Akbank Axess")).findFirst().get().getCampaignList().size()).isEqualTo(1);
        assertThat(creditCardResponseList.stream().filter(resp -> resp.getName().equals("Garanti Bonus")).findFirst().get().getCampaignList().size()).isEqualTo(1);

        //verify
        Mockito.verify(akbankServiceClient, Mockito.times(1)).getCreditCards();
        Mockito.verify(garantiServiceClient, Mockito.times(1)).getCreditCards();
    }

    public List<CreditCardResponse> prepareAkbankCreditCards() {
        List<Campaign> campaignList = new ArrayList<>();
        campaignList.add(Campaign.builder().id(122L).content("10% discount").createDate(LocalDate.of(2021, 1, 1)).dueDate(LocalDate.of(2021, 1, 31)).title("New Year Campaign").build());
        
        return List.of(
            CreditCardResponse.builder()
                .id(1L)
                .name("Akbank Axess")
                .bank("akbank")
                .fee(BigDecimal.valueOf(100))
                .campaignList(campaignList)
                .build()
        );
    }

    public List<CreditCardResponse> prepareGarantiCreditCards() {
        List<Campaign> campaignList = new ArrayList<>();
        campaignList.add(Campaign.builder().id(124L).content("12% discount").createDate(LocalDate.of(2021, 1, 1)).dueDate(LocalDate.of(2021, 1, 31)).title("New Year Campaign").build());
        
        return List.of(
            CreditCardResponse.builder()
                .id(1L)
                .name("Garanti Bonus")
                .bank("garanti")
                .fee(BigDecimal.valueOf(250))
                .campaignList(campaignList)
                .build()
        );
    }
}
