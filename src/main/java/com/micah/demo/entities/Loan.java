package com.micah.demo.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;

@ToString
@Setter
@Getter
@Entity
@Accessors(chain = true)
@NoArgsConstructor
@Table(name = "loan")
public class Loan extends Auditable{
    @Id
    private String loanId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "borrower", nullable = false)
    private Account borrower;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "lender", nullable = false)
    private Account lender;

    private double amount;

    public Loan(Loan loan) {
        this.loanId = loan.getLoanId();
        this.borrower = loan.getBorrower();
        this.lender = loan.getLender();
        this.amount = loan.getAmount();
    }
}
