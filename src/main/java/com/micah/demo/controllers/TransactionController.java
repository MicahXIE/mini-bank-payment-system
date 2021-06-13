package com.micah.demo.controllers;

import com.micah.demo.auth.UserContext;
import com.micah.demo.controllers.responses.PayResponse;
import com.micah.demo.controllers.responses.TopupResponse;
import com.micah.demo.services.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping(path = "/topup", produces = "application/json")
    public ResponseEntity<TopupResponse> topup(@Positive @RequestParam double amount) {
        String username = UserContext.getCurrentUserName();
        return ResponseEntity.ok(transactionService.topupAccount(username, amount));
    }

    @PostMapping(path = "/pay", produces = "application/json")
    public ResponseEntity<PayResponse> pay(@NotBlank @RequestParam String recipient,
                                           @Positive @RequestParam double amount) {
        String payer = UserContext.getCurrentUserName();
        return ResponseEntity.ok(transactionService.transfer(payer, recipient, amount));
    }
}
