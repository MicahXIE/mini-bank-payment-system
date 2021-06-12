package com.micah.demo.controllers.responses;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TransactionData {
    private String transactionId;
    private String payerName;
    private String recipientName;
    private double amount;
}
