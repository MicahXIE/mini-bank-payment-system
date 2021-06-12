package com.micah.demo.controllers.responses;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TopupResponse {
    AccountData account;
    List<TransactionData> transactionInfo;
    List<LoanData> loanInfo;
}
