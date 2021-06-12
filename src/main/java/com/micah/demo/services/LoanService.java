package com.micah.demo.services;

import com.micah.demo.entities.Loan;
import com.micah.demo.repositories.LoanRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanService {
    private final LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public List<Loan> getUserLoanInfo(String username) {
        List<Loan> loanList = loanRepository.findByUsername(username);
        return loanList;
    }

    public List<Loan> getBorrowerLoanInfo(String username) {
        List<Loan> loanList = loanRepository.findByBorrowerName(username);
        return loanList;
    }

    public Loan getBorrowerAndLenderLoanInfo(String borrowerName, String lenderName) {
        Optional<Loan> optionalLoan = loanRepository.findByBorrowerNameAndLenderName(borrowerName, lenderName);
        return optionalLoan.isPresent() ? optionalLoan.get() : null;
    }
}
