package com.patika.kredinbizdeservice.client.dto.response;

import lombok.*;

import java.time.LocalDateTime;

import com.patika.kredinbizdeservice.model.Loan;
import com.patika.kredinbizdeservice.model.Product;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ApplicationResponse {

    private Long userId;
    private LocalDateTime createDate;
    private ApplicationStatus applicationStatus;
    private Loan loan;
}
