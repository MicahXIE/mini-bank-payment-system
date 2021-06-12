package com.micah.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController {

//    @PostMapping(path = "/topup", produces = "application/json")
//    public ResponseEntity<TopupResponse> topup(@Valid @RequestParam double amount) {
//        return ResponseEntity.ok(responseHelpers.from(accountService.login(username)).toAccountResponse());
//    }

//    @PostMapping(path = "/pay", produces = "application/json")
//    public ResponseEntity<AccountResponse> topup(@NotBlank @RequestParam String username,
//                                                 @RequestParam double amount) {
//        return ResponseEntity.ok(responseHelpers.from(accountService.login(username)).toAccountResponse());
//    }
}
