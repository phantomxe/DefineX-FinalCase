package com.patika.kredinbizdeservice.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.joda.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.patika.kredinbizdeservice.client.AkbankServiceClient;
import com.patika.kredinbizdeservice.client.GarantiServiceClient;
import com.patika.kredinbizdeservice.client.dto.request.AkbankApplicationRequest;
import com.patika.kredinbizdeservice.client.dto.request.GarantiApplicationRequest;
import com.patika.kredinbizdeservice.client.dto.response.ApplicationResponse;
import com.patika.kredinbizdeservice.client.dto.response.ApplicationStatus;
import com.patika.kredinbizdeservice.dto.request.ApplicationRequest;
import com.patika.kredinbizdeservice.enums.LoanType;
import com.patika.kredinbizdeservice.model.Address;
import com.patika.kredinbizdeservice.model.Loan;
import com.patika.kredinbizdeservice.model.User;
import com.patika.kredinbizdeservice.repository.AddressRepository;
import com.patika.kredinbizdeservice.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class ApplicationServiceTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private ApplicationService applicationService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private AkbankServiceClient akbankServiceClient;

    @Mock 
    private GarantiServiceClient garantiServiceClient;
    
    @Test
    public void should_create_application_at_akbank_successfully() {
        
        Mockito.when(userService.getByEmail(prepareUser().getEmail())).thenReturn(prepareUser());

        Mockito.when(akbankServiceClient.createApplication(Mockito.any(AkbankApplicationRequest.class)))
            .thenReturn(getApplicationFromRequestAkbank(prepareAkbankApplicationRequest()));
    
        //when 
        ApplicationRequest applicationRequest = getApplicationAkbankFromRequest(prepareAkbankApplicationRequest());
        ApplicationResponse applicationResponse = applicationService.createApplication(applicationRequest);

        //then
        System.out.println(applicationResponse.toString());

        assertThat(applicationResponse).isNotNull();
        assertThat(applicationResponse.getLoan().getBank()).isEqualTo("akbank");
        assertThat(applicationResponse.getLoan().getInterestRate()).isEqualTo(1.5);
        assertThat(applicationResponse.getLoan().getAmount()).isEqualTo(BigDecimal.valueOf(150000));
        assertThat(applicationResponse.getLoan().getInstallment()).isEqualTo(12);
        assertThat(applicationResponse.getApplicationStatus()).isEqualTo(ApplicationStatus.INITIAL);

        //verify
        Mockito.verify(akbankServiceClient, Mockito.times(1)).createApplication(Mockito.any(AkbankApplicationRequest.class));

    }

    @Test
    public void should_create_application_at_garanti_successfully() {
        
        Mockito.when(userService.getByEmail(prepareUser().getEmail())).thenReturn(prepareUser());

        Mockito.when(akbankServiceClient.createApplication(Mockito.any(AkbankApplicationRequest.class)))
            .thenThrow(new RuntimeException("No loan found."));

        Mockito.when(garantiServiceClient.createApplication(Mockito.any(GarantiApplicationRequest.class)))
            .thenReturn(getApplicationFromRequestGaranti(prepareGarantiApplicationRequest()));
    
        //when 
        ApplicationRequest applicationRequest = getApplicationGarantiFromRequest(prepareGarantiApplicationRequest());
        ApplicationResponse applicationResponse = applicationService.createApplication(applicationRequest);

        //then
        System.out.println(applicationRequest.toString());
        System.out.println(applicationResponse.toString());

        assertThat(applicationResponse).isNotNull();
        assertThat(applicationResponse.getLoan().getBank()).isEqualTo("garanti");
        assertThat(applicationResponse.getLoan().getInterestRate()).isEqualTo(2.99);
        assertThat(applicationResponse.getLoan().getAmount()).isEqualTo(BigDecimal.valueOf(20000));
        assertThat(applicationResponse.getLoan().getInstallment()).isEqualTo(12);
        assertThat(applicationResponse.getApplicationStatus()).isEqualTo(ApplicationStatus.INITIAL);

        //verify
        Mockito.verify(garantiServiceClient, Mockito.times(1)).createApplication(Mockito.any(GarantiApplicationRequest.class));

    }

    private GarantiApplicationRequest prepareGarantiApplicationRequest() {
        GarantiApplicationRequest garantiApplicationRequest = new GarantiApplicationRequest();

        garantiApplicationRequest.setUserId(1L);
        garantiApplicationRequest.setAmount(BigDecimal.valueOf(20000));
        garantiApplicationRequest.setInstallment(12);
        garantiApplicationRequest.setLoanType(LoanType.IHTIYAC_KREDISI);

        return garantiApplicationRequest;
    }

    private ApplicationRequest getApplicationGarantiFromRequest(GarantiApplicationRequest request) {  
        ApplicationRequest applicationRequest = new ApplicationRequest();

        applicationRequest.setEmail("test@test.com");
        applicationRequest.setAmount(request.getAmount());
        applicationRequest.setInstallment(request.getInstallment());
        applicationRequest.setLoanType(request.getLoanType());

        return applicationRequest;
    }
    
    private AkbankApplicationRequest prepareAkbankApplicationRequest() {
        AkbankApplicationRequest akbankApplicationRequest = new AkbankApplicationRequest();

        akbankApplicationRequest.setUserId(1L);
        akbankApplicationRequest.setAmount(BigDecimal.valueOf(150000));
        akbankApplicationRequest.setInstallment(12);
        akbankApplicationRequest.setLoanType(LoanType.ARAC_KREDISI);

        return akbankApplicationRequest;
    }

    private ApplicationRequest getApplicationAkbankFromRequest(AkbankApplicationRequest request) {  
        ApplicationRequest applicationRequest = new ApplicationRequest();

        applicationRequest.setEmail("test@test.com");
        applicationRequest.setAmount(request.getAmount());
        applicationRequest.setInstallment(request.getInstallment());
        applicationRequest.setLoanType(request.getLoanType());

        return applicationRequest;
    } 

    private User prepareUser() {
        User user = new User();
        Address address = new Address();

        address.setAddressTitle("test address title");
        address.setAddressDescription("test address description");
        address.setProvince("test province");

        user.setId(1L);
        user.setName("test name");
        user.setSurname("test surname");
        user.setEmail("test@test.com");
        user.setPassword("password");  
        user.setAddress(address);

        return user;
    }

    private ApplicationResponse getApplicationFromRequestGaranti(GarantiApplicationRequest request) {
        Loan loan = new Loan();
        loan.setAmount(request.getAmount());
        loan.setInstallment(request.getInstallment());
        loan.setBank("garanti");
        loan.setInterestRate(Double.valueOf(2.99));


        return ApplicationResponse.builder() 
                .userId(request.getUserId())
                .applicationStatus(ApplicationStatus.INITIAL)
                .createDate(LocalDateTime.now())
                .loan(loan)
                .build();
    }

    private ApplicationResponse getApplicationFromRequestAkbank(AkbankApplicationRequest request) {
        Loan loan = new Loan();
        loan.setAmount(request.getAmount());
        loan.setInstallment(request.getInstallment());
        loan.setBank("akbank");
        loan.setInterestRate(Double.valueOf(1.5));


        return ApplicationResponse.builder() 
                .userId(request.getUserId())
                .applicationStatus(ApplicationStatus.INITIAL)
                .createDate(LocalDateTime.now())
                .loan(loan)
                .build();
    }
}
