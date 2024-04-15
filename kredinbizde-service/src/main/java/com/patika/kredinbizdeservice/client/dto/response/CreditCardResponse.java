package com.patika.kredinbizdeservice.client.dto.response;

import java.math.BigDecimal;
import java.util.List;

import com.patika.kredinbizdeservice.model.Campaign;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CreditCardResponse { 
    private Long id; 
    private String name; 
    private BigDecimal fee; 
    private List<Campaign> campaignList; 
    private String bank;
}
