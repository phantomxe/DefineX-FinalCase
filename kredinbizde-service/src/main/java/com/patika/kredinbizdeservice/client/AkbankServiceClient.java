package com.patika.kredinbizdeservice.client;

import com.patika.kredinbizdeservice.client.dto.request.AkbankApplicationRequest;
import com.patika.kredinbizdeservice.client.dto.response.ApplicationResponse;
import com.patika.kredinbizdeservice.client.dto.response.CreditCardResponse; 

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@FeignClient(value = "akbank-service", url = "localhost:8081")
@FeignClient("akbank-service")
public interface AkbankServiceClient {

    @PostMapping("api/akbank/v1/applications")
    ApplicationResponse createApplication(@RequestBody AkbankApplicationRequest request);

    @GetMapping("api/akbank/v1/applications")
    List<ApplicationResponse> getApplications();

    @GetMapping("api/akbank/v1/applications/{userId}")
    List<ApplicationResponse> getApplicationsByUserId(@PathVariable Long userId);

    @GetMapping("api/akbank/v1/cards")
    List<CreditCardResponse> getCreditCards();
}
