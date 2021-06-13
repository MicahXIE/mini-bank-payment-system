package com.micah.demo.controllers.responses;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TopupResponse {
    private AccountData account;
    private List<TransactionData> transactionInfo;
    private List<LoanData> loanInfo;
}
