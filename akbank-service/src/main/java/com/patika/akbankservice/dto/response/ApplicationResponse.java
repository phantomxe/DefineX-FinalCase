package com.patika.akbankservice.dto.response;

import com.patika.akbankservice.entity.Loan;
import com.patika.akbankservice.entity.Product;
import com.patika.akbankservice.enums.ApplicationStatus;
import lombok.*;

import java.time.LocalDateTime;

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
