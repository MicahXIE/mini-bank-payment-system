package com.micah.demo.controllers.responses;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PayResponse {
    AccountData account;
    LoanData preLoan;
    TransactionData transactionInfo;
    LoanData postLoan;
}
