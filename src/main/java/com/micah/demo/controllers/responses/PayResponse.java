package com.micah.demo.controllers.responses;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PayResponse {
    private AccountData account;
    private LoanData preLoan;
    private TransactionData transactionInfo;
    private LoanData postLoan;
}
