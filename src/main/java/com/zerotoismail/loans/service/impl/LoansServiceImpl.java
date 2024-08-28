package com.zerotoismail.loans.service.impl;

import com.zerotoismail.loans.constants.LoansConstants;
import com.zerotoismail.loans.dto.LoansDto;
import com.zerotoismail.loans.entity.LoansEntity;
import com.zerotoismail.loans.exception.LoanAlreadyExistsException;
import com.zerotoismail.loans.exception.ResourceNotFoundException;
import com.zerotoismail.loans.mapper.LoansMapper;
import com.zerotoismail.loans.repository.LoansRepository;
import com.zerotoismail.loans.service.ILoansService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerMapping;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoansServiceImpl implements ILoansService {

    private final HandlerMapping resourceHandlerMapping;
    private LoansRepository repository;

    @Override
    public void createLoan(String mobileNumber) {
        Optional<LoansEntity> optionalLoan = repository.findByMobileNumber(mobileNumber);
        if(optionalLoan.isPresent()) {
            throw new LoanAlreadyExistsException("Loan already registered with given mobileNumber "+mobileNumber);
        }
        repository.save(createNewLoan(mobileNumber));
    }

    private LoansEntity createNewLoan(String mobileNumber) {
        LoansEntity newLoan = new LoansEntity();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoansConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        return newLoan;
    }

    @Override
    public LoansDto fetchLoan(String mobileNumber) {
        LoansEntity loanEntity = repository.findByMobileNumber(mobileNumber).orElseThrow(
        () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
        );

        LoansDto loansDto = new LoansDto();
        LoansMapper.mapToLoansDto(loanEntity, loansDto);
        return loansDto;
    }

    @Override
    public boolean updateLoan(LoansDto loansDto) {
        LoansEntity loansEntity = repository.findByLoanNumber(loansDto.getLoanNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "Loan Number", loansDto.getLoanNumber())
        );

        LoansMapper.mapToLoans(loansDto, loansEntity);
        repository.save(loansEntity);
        return true;
    }

    @Override
    public boolean deleteLoan(String mobileNumber) {
        repository.deleteByMobileNumber(mobileNumber);
        return true;
    }
}
