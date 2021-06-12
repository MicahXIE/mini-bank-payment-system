package com.micah.demo.controllers.responses;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoanData {
    private String loanId;
    private String borrowerName;
    private String lenderName;
    private double amount;
}
