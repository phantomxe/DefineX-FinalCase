package com.patika.akbankservice.dto.request;

import java.time.LocalDate;

import com.patika.akbankservice.enums.SectorType;

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
public class CampaignInner {
    private String title;
    private String content; 
    private LocalDate dueDate; 
    private LocalDate createDate; 
    private LocalDate updateDate; 
    private SectorType sector;
}
