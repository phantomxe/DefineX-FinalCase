package com.patika.akbankservice.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
 
import com.patika.akbankservice.converter.ApplicationConverter;
import com.patika.akbankservice.dto.request.ApplicationRequest;
import com.patika.akbankservice.dto.response.ApplicationResponse;
import com.patika.akbankservice.entity.Application;
import com.patika.akbankservice.entity.Loan;
import com.patika.akbankservice.enums.LoanType;
import com.patika.akbankservice.repository.ApplicationRepository;
import com.patika.akbankservice.repository.LoanRepository;

@ExtendWith(MockitoExtension.class)
public class ApplicationServiceTest {
    
    @InjectMocks
    private ApplicationService applicationService;

    @Mock
    private ApplicationRepository applicationRepository;

    @Mock
    private LoanRepository loanRepository;

    @Mock
    private ApplicationConverter applicationConverter;


    @Test
    public void should_create_application() { 

        Mockito.when(loanRepository.save(Mockito.any(Loan.class))).thenReturn(prepareLoan());
        Mockito.when(applicationConverter.toApplication(Mockito.any(ApplicationRequest.class))).thenReturn(prepareApplication(generatePossibleRequest(), prepareLoan()));
        Mockito.when(applicationConverter.toResponse(Mockito.any(Application.class))).thenReturn(prepareResponse(prepareApplication(generatePossibleRequest(), prepareLoan())));
 
        ApplicationRequest applicationRequest = generatePossibleRequest();
        ApplicationResponse applicationResponse = applicationService.createApplication(applicationRequest);

        System.out.println(applicationResponse.toString());
        assertThat(applicationResponse).isNotNull();
        assertThat(applicationResponse.getLoan()).isNotNull();
        assertThat(applicationResponse.getLoan().getAmount()).isEqualTo(applicationRequest.getAmount());
        assertThat(applicationResponse.getLoan().getInstallment()).isEqualTo(applicationRequest.getInstallment());
        assertThat(applicationResponse.getLoan().getLoanType()).isEqualTo(applicationRequest.getLoanType());
        assertThat(applicationResponse.getLoan().getInterestRate()).isEqualTo(3.49);

        Mockito.verify(loanRepository, Mockito.times(1)).save(Mockito.any());
        Mockito.verify(applicationRepository, Mockito.times(1)).save(Mockito.any());
    }

    private ApplicationRequest generatePossibleRequest() {
        ApplicationRequest applicationRequest = new ApplicationRequest();
        applicationRequest.setAmount(BigDecimal.valueOf(300000));
        applicationRequest.setInstallment(12);
        applicationRequest.setLoanType(LoanType.KONUT_KREDISI);
        applicationRequest.setUserId(1L);
        return applicationRequest;
    }

    private Application prepareApplication(ApplicationRequest request, Loan loan) {
        Application application = new Application();
        application.setId(24912L);
        application.setUserId(request.getUserId());
        application.setCreateDate(LocalDateTime.now());
        application.setLoan(loan);
        return application;
    }

    private ApplicationResponse prepareResponse(Application application) {
        ApplicationResponse response = new ApplicationResponse();
        response.setUserId(application.getId());
        response.setUserId(application.getUserId());
        response.setCreateDate(application.getCreateDate());
        response.setLoan(application.getLoan());
        return response;
    }

    private Loan prepareLoan() {
        Loan loan = new Loan();
        loan.setId(10001L);
        loan.setAmount(BigDecimal.valueOf(300000));
        loan.setInstallment(12);
        loan.setLoanType(LoanType.KONUT_KREDISI);
        loan.setInterestRate(3.49);
        return loan;
    }
}
