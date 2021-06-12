package com.micah.demo.services;

import com.micah.demo.entities.Loan;
import com.micah.demo.repositories.LoanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
