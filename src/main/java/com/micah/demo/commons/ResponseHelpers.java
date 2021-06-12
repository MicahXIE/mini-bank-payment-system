package com.micah.demo.commons;

import com.micah.demo.controllers.responses.AccountData;
import com.micah.demo.controllers.responses.LoanData;
import com.micah.demo.controllers.responses.LoginResponse;
import com.micah.demo.entities.Account;
import com.micah.demo.entities.Loan;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ResponseHelpers {

    public class AccountDataHelper {
        Account account;

        public AccountDataHelper(Account account) {
            this.account = account;
        }

        public AccountData toAccountData() {
            AccountData accountData = new AccountData();
            accountData.setUsername(account.getUsername());
            accountData.setBalance(account.getBalance());
            return accountData;
        }
    }

    public class LoanDataHelper {
        Loan loan;

        public LoanDataHelper(Loan loan) { this.loan = loan; }

        public LoanData toLoanData() {
            LoanData loanData = new LoanData();
            loanData.setLoanId(loan.getLoanId());
            loanData.setBorrowerName(loan.getBorrower().getUsername());
            loanData.setLenderName(loan.getLender().getUsername());
            loanData.setAmount(loan.getAmount());
            return loanData;

        }
    }

    public class LoginResponseHelper {
        Account account;
        List<Loan> loanList;

        public LoginResponseHelper(Account account, List<Loan> loanList) {
            this.account = account;
            this.loanList = loanList;
        }

        public LoginResponse toLoginResponse() {
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setAccount(from(account).toAccountData());
            loginResponse.setLoanInfo(loanList.stream().map(loan -> from(loan).toLoanData()).collect(Collectors.toList()));
            return loginResponse;
        }
    }

    public AccountDataHelper from(Account account) {
        return new AccountDataHelper(account);
    }

    public LoanDataHelper from(Loan loan) { return new LoanDataHelper(loan); }

    public LoginResponseHelper from(Account account, List<Loan> loanList) { return new LoginResponseHelper(account, loanList); }
}
