package com.patika.kredinbizdeservice.model;

import com.patika.kredinbizdeservice.enums.SectorType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder 
public class Campaign { 
    private Long id; 
    private String title; 
    private String content; 
    private LocalDate dueDate; 
    private LocalDate createDate; 
    private LocalDate updateDate;  
    private SectorType sector;
}

