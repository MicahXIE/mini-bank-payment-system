package com.micah.demo.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;

@ToString
@Setter
@Getter
@Entity
@Accessors(chain = true)
@Table(name = "transaction")
public class Transaction extends Auditable{
    @Id
    private String transactionId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "payer", nullable = false)
    private Account payer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "recipient", nullable = false)
    private Account recipient;

    private double amount;
}
