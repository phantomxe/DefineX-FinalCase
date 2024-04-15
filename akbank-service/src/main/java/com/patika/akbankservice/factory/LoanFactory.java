package com.patika.akbankservice.factory;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.patika.akbankservice.entity.Loan;
import com.patika.akbankservice.entity.LoanLimit;
import com.patika.akbankservice.enums.LoanType;
import com.patika.akbankservice.exceptions.NoSuitableLoanException;


public class LoanFactory {
    private Map<LoanType, List<LoanLimit>> interestRates = new HashMap<>();

    public LoanFactory() {
        interestRates.put(LoanType.ARAC_KREDISI, List.of(
            new LoanLimit(BigDecimal.valueOf(150000), BigDecimal.valueOf(200000), 1.99, 12),
            new LoanLimit(BigDecimal.valueOf(200000), BigDecimal.valueOf(400000), 2.49, 24),
            new LoanLimit(BigDecimal.valueOf(400000), BigDecimal.valueOf(690000), 3.10, 36))); 

        interestRates.put(LoanType.KONUT_KREDISI, List.of(
            new LoanLimit(BigDecimal.valueOf(300000), BigDecimal.valueOf(800000), 3.49, 12),
            new LoanLimit(BigDecimal.valueOf(10000000), BigDecimal.valueOf(15000000), 6.55, 48)));
    }
    
    public Loan generateLoan(LoanType loanType, BigDecimal amount, Integer installment) { 
        List<LoanLimit> allLimits = interestRates.get(loanType);

        if(allLimits == null) {
            throw new NoSuitableLoanException("Loan type not found!");
        }

        List<LoanLimit> mylimits = allLimits.stream()
            .filter(limit -> limit.getInstallment().equals(installment))
            .toList();

        if(mylimits.size() == 0) {
            throw new NoSuitableLoanException("Loan not found!");
        }
 
        for(LoanLimit limit : mylimits) {
            if(limit.getMinAmount().compareTo(amount) >= 0 && limit.getMaxAmount().compareTo(amount) <= 0){
                return Loan.builder().amount(amount).installment(installment).interestRate(limit.getInterestRate()).loanType(loanType).build();   // (amount, installment, limit.getInterestRate(), loanType);
            }
        }

        throw new NoSuitableLoanException("There is no suitable loan!");
    }
}
