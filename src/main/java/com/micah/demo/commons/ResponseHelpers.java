package com.micah.demo.commons;

import com.micah.demo.controllers.responses.*;
import com.micah.demo.entities.Account;
import com.micah.demo.entities.Loan;
import com.micah.demo.entities.Transaction;
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
            if (account == null)
                return accountData;
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
            if (loan == null)
                return  loanData;
            loanData.setLoanId(loan.getLoanId());
            loanData.setBorrowerName(loan.getBorrower().getUsername());
            loanData.setLenderName(loan.getLender().getUsername());
            loanData.setAmount(loan.getAmount());
            return loanData;
        }
    }

    public class TransactionDataHelper {
        Transaction transaction;

        public TransactionDataHelper(Transaction transaction) {
            this.transaction = transaction;
        }

        public TransactionData toTransactionData() {
            TransactionData transactionData = new TransactionData();
            if (transaction == null)
                return transactionData;
            transactionData.setTransactionId(transaction.getTransactionId());
            transactionData.setPayerName(transaction.getPayer().getUsername());
            transactionData.setRecipientName(transaction.getRecipient().getUsername());
            transactionData.setAmount(transaction.getAmount());
            return transactionData;
        }
    }

    public class LoginResponseHelper {
        String token;
        Account account;
        List<Loan> loanList;

        public LoginResponseHelper(String token, Account account, List<Loan> loanList) {
            this.token = token;
            this.account = account;
            this.loanList = loanList;
        }

        public LoginResponse toLoginResponse() {
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setToken(token);
            loginResponse.setAccount(from(account).toAccountData());
            loginResponse.setLoanInfo(loanList.stream().map(loan -> from(loan).toLoanData()).collect(Collectors.toList()));
            return loginResponse;
        }
    }

    public class TopupResponseHelper {
        Account account;
        List<Loan> loanList;
        List<Transaction> transactionList;

        public TopupResponseHelper(Account account, List<Loan> loanList, List<Transaction> transactionList) {
            this.account = account;
            this.loanList = loanList;
            this.transactionList = transactionList;
        }

        public TopupResponse toTopupResponse() {
            TopupResponse topupResponse = new TopupResponse();
            topupResponse.setAccount(from(account).toAccountData());
            topupResponse.setLoanInfo(loanList.stream().map(loan -> from(loan).toLoanData()).collect(Collectors.toList()));
            topupResponse.setTransactionInfo(transactionList.stream().map(transaction -> from(transaction).toTransactionData()).collect(Collectors.toList()));
            return topupResponse;
        }

    }

    public class PayResponseHelper {
        Account account;
        Loan preLoan;
        Transaction transaction;
        Loan postLoan;

        public PayResponseHelper(Account account, Loan preLoan, Transaction transaction, Loan postLoan) {
            this.account = account;
            this.preLoan = preLoan;
            this.transaction = transaction;
            this.postLoan = postLoan;
        }

        public PayResponse toPayResponse() {
            PayResponse payResponse = new PayResponse();
            payResponse.setAccount(from(account).toAccountData());
            payResponse.setPreLoan(from(preLoan).toLoanData());
            payResponse.setTransactionInfo(from(transaction).toTransactionData());
            payResponse.setPostLoan(from(postLoan).toLoanData());
            return payResponse;
        }

    }

    public AccountDataHelper from(Account account) { return new AccountDataHelper(account); }

    public LoanDataHelper from(Loan loan) { return new LoanDataHelper(loan); }

    public TransactionDataHelper from(Transaction transaction) { return new TransactionDataHelper(transaction); }

    public LoginResponseHelper from(String token, Account account, List<Loan> loanList) { return new LoginResponseHelper(token, account, loanList); }

    public TopupResponseHelper from(Account account, List<Loan> loanList, List<Transaction> transactionList) { return new TopupResponseHelper(account, loanList, transactionList); }

    public PayResponseHelper from(Account account, Loan preLoan, Transaction transaction, Loan postLoan) { return new PayResponseHelper(account, preLoan, transaction, postLoan); }
}
