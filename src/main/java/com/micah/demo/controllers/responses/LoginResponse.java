package com.micah.demo.controllers.responses;

import com.micah.demo.entities.Account;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class LoginResponse {
    AccountData account;
    List<LoanData> loanInfo;
}
