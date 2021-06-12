package com.micah.demo.services;

import com.micah.demo.commons.ResponseHelpers;
import com.micah.demo.commons.utils.IdGenerator;
import com.micah.demo.controllers.responses.PayResponse;
import com.micah.demo.controllers.responses.TopupResponse;
import com.micah.demo.entities.Account;
import com.micah.demo.entities.Loan;
import com.micah.demo.entities.Transaction;
import com.micah.demo.exceptions.AccountNotFoundException;
import com.micah.demo.repositories.AccountRepository;
import com.micah.demo.repositories.LoanRepository;
import com.micah.demo.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {
    private final AccountRepository accountRepository;
    private final LoanRepository loanRepository;
    private final TransactionRepository transactionRepository;
    private final AccountService accountService;
    private final LoanService loanService;
    private final ResponseHelpers responseHelpers;

    public TransactionService(AccountRepository accountRepository, LoanRepository loanRepository, TransactionRepository transactionRepository,
                              AccountService accountService, LoanService loanService, ResponseHelpers responseHelpers) {
        this.accountRepository = accountRepository;
        this.loanRepository = loanRepository;
        this.transactionRepository = transactionRepository;
        this.accountService = accountService;
        this.loanService = loanService;
        this.responseHelpers = responseHelpers;
    }

    public TopupResponse topupAccount(String username, double amount) {
        Account account = accountService.getAccountInfo(username);
        if (account == null)
            throw new AccountNotFoundException(MessageFormat.format("topup account {0} not found", username));

        List<Transaction> transactionList = new ArrayList<>();
        List<Loan> loanList = loanService.getBorrowerLoanInfo(username);
        List<Loan> deletedLoanList = new ArrayList<>();
        List<Loan> remainingLoanList = new ArrayList<>();
        if (loanList.isEmpty()) {
            account.setBalance(account.getBalance() + amount);
        } else {
            double currentBalance = account.getBalance() + amount;
            for (Loan loan : loanList) {
                if (currentBalance > 0) {
                    if (currentBalance > loan.getAmount()) {
                        currentBalance = currentBalance - loan.getAmount();
                        deletedLoanList.add(loan);
                        transactionList.add(new Transaction()
                                .setTransactionId(IdGenerator.generateTransactionId())
                                .setPayer(loan.getBorrower())
                                .setRecipient(loan.getLender())
                                .setAmount(loan.getAmount()));
                        Account lenderAccount = accountService.getAccountInfo(loan.getLender().getUsername());
                        lenderAccount.setBalance(lenderAccount.getBalance() + loan.getAmount());
                        accountRepository.save(lenderAccount);
                    } else {
                        loan.setAmount(loan.getAmount() - currentBalance);
                        currentBalance = 0.0d;
                        loan = loanRepository.save(loan);
                        remainingLoanList.add(loan);
                    }
                } else {
                    remainingLoanList.add(loan);
                }

            }
            account.setBalance(currentBalance);
        }

        accountRepository.save(account);
        if (!transactionList.isEmpty())
            transactionRepository.saveAll(transactionList);
        if (!deletedLoanList.isEmpty())
            loanRepository.deleteAll(deletedLoanList);

        return responseHelpers.from(account, remainingLoanList, transactionList).toTopupResponse();
    }

    public PayResponse transfer(String payerName, String recipientName, double amount) {
        Account payerAccount = accountService.getAccountInfo(payerName);
        if (payerAccount == null)
            throw new AccountNotFoundException(MessageFormat.format("payer account {0} not found", payerName));

        Account recipientAccount = accountService.getAccountInfo(recipientName);
        if (recipientAccount == null)
            throw new AccountNotFoundException(MessageFormat.format("recipient account {0} not found", recipientName));

        Transaction newTransaction = null;
        Loan preLoan = null;
        Loan postLoan = null;
        double payerBalance = payerAccount.getBalance();
        double recipientBalance = recipientAccount.getBalance();
        // payer owe money to recipient
        Loan payerLoan = loanService.getBorrowerAndLenderLoanInfo(payerName, recipientName);
        // recipient owe money to payer
        Loan recipientLoan = loanService.getBorrowerAndLenderLoanInfo(recipientName, payerName);

        if (payerLoan == null) {    // payer doesn't owe money to recipient
            if (recipientLoan == null) {    // recipient doesn't owe money to payer
                if (payerBalance >= amount) {
                    payerAccount.setBalance(payerBalance - amount);
                    recipientAccount.setBalance(recipientBalance + amount);
                    newTransaction = new Transaction().setTransactionId(IdGenerator.generateTransactionId())
                            .setPayer(payerAccount)
                            .setRecipient(recipientAccount)
                            .setAmount(amount);
                } else {
                    payerAccount.setBalance(0.0);
                    recipientAccount.setBalance(recipientBalance + payerBalance);
                    newTransaction = new Transaction().setTransactionId(IdGenerator.generateTransactionId())
                            .setPayer(payerAccount)
                            .setRecipient(recipientAccount)
                            .setAmount(payerBalance);
                    postLoan = new Loan().setLoanId(IdGenerator.generateLoanId())
                            .setBorrower(payerAccount)
                            .setLender(recipientAccount)
                            .setAmount(amount - payerBalance);
                }
            } else {    // recipient owe money to payer
                preLoan = recipientLoan;
                if (recipientLoan.getAmount() > amount) {
                    recipientLoan.setAmount(recipientLoan.getAmount() - amount);
                    postLoan = loanRepository.save(recipientLoan);
                } else {
                    double transferAmt = amount - recipientLoan.getAmount();
                    if (payerBalance >= transferAmt) {
                        payerAccount.setBalance(payerBalance - transferAmt);
                        recipientAccount.setBalance(recipientBalance + transferAmt);
                        newTransaction = new Transaction().setTransactionId(IdGenerator.generateTransactionId())
                                .setPayer(payerAccount)
                                .setRecipient(recipientAccount)
                                .setAmount(transferAmt);
                        loanRepository.delete(recipientLoan);
                    } else {
                        payerAccount.setBalance(0.0d);
                        recipientAccount.setBalance(recipientBalance + payerBalance);
                        newTransaction = new Transaction().setTransactionId(IdGenerator.generateTransactionId())
                                .setPayer(payerAccount)
                                .setRecipient(recipientAccount)
                                .setAmount(payerBalance);
                        postLoan = new Loan().setLoanId(IdGenerator.generateLoanId())
                                .setBorrower(payerAccount)
                                .setLender(recipientAccount)
                                .setAmount(amount - payerBalance);
                    }
                }
            }
        } else {    // payer owe money to recipient
            preLoan = payerLoan;
            double totalAmt = payerLoan.getAmount() + amount;
            if (payerBalance > totalAmt) {
                payerAccount.setBalance(payerBalance - totalAmt);
                recipientAccount.setBalance(recipientBalance + totalAmt);
                newTransaction = new Transaction().setTransactionId(IdGenerator.generateTransactionId())
                        .setPayer(payerAccount)
                        .setRecipient(recipientAccount)
                        .setAmount(totalAmt);
                loanRepository.delete(payerLoan);
            } else {
                payerAccount.setBalance(0.0d);
                recipientAccount.setBalance(payerBalance + payerBalance);
                if (payerBalance > 0) {
                    newTransaction = new Transaction().setTransactionId(IdGenerator.generateTransactionId())
                            .setPayer(payerAccount)
                            .setRecipient(recipientAccount)
                            .setAmount(payerBalance);
                }
                payerLoan.setAmount(totalAmt - payerBalance);
                postLoan = loanRepository.save(payerLoan);
            }
        }

        return responseHelpers.from(payerAccount, preLoan, newTransaction, postLoan).toPayResponse();
    }
}
