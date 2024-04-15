package com.patika.kredinbizdeservice.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.patika.kredinbizdeservice.client.dto.request.GarantiApplicationRequest;
import com.patika.kredinbizdeservice.client.dto.response.ApplicationResponse;
import com.patika.kredinbizdeservice.client.dto.response.CreditCardResponse;

@FeignClient("garanti-service")
public interface GarantiServiceClient {

    @PostMapping("api/garanti/v1/applications")
    ApplicationResponse createApplication(@RequestBody GarantiApplicationRequest request);

    @GetMapping("api/garanti/v1/applications")
    List<ApplicationResponse> getApplications();

    @GetMapping("api/garanti/v1/applications/{userId}")
    List<ApplicationResponse> getApplicationsByUserId(@PathVariable Long userId);

    @GetMapping("api/akbank/v1/cards")
    List<CreditCardResponse> getCreditCards();
}
