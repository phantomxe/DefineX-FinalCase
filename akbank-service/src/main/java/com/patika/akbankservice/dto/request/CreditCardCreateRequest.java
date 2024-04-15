package com.patika.akbankservice.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
 

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreditCardCreateRequest {
    private String name;
    private BigDecimal fee;
    private List<CampaignInner> campaignList;
}
