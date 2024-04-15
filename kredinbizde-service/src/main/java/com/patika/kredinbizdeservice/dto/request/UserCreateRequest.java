package com.patika.kredinbizdeservice.dto.request;

import java.time.LocalDate;
 
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserCreateRequest { 
    private String name; 
    private String surname; 
    private LocalDate birthDate; 
    private String email; 
    private String password; 
    private String phoneNumber; 
    private String addressTitle; 
    private String addressDescription; 
    private String province;
}
