package com.micah.demo.controllers;

import com.micah.demo.commons.ResponseHelpers;
import com.micah.demo.controllers.responses.AccountData;
import com.micah.demo.controllers.responses.LoginResponse;
import com.micah.demo.services.AccountService;
import com.micah.demo.services.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/api/v1/user")
public class AccountController {
    private final AccountService accountService;
    private final ResponseHelpers responseHelpers;
    private final LoanService loanService;

    public AccountController(AccountService accountService, ResponseHelpers responseHelpers, LoanService loanService) {
        this.accountService = accountService;
        this.responseHelpers = responseHelpers;
        this.loanService = loanService;
    }

    @PostMapping(path = "/login", produces = "application/json")
    public ResponseEntity<LoginResponse> login(@NotBlank @RequestParam String username) {
        return ResponseEntity.ok(responseHelpers.from(accountService.login(username), loanService.getUserLoanInfo(username)).toLoginResponse());
    }

}
