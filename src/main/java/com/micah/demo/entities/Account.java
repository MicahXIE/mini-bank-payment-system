package com.micah.demo.entities;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@ToString
@Data
@NoArgsConstructor
@Entity
@Accessors(chain = true)
@Table(name = "account")
public class Account extends Auditable{
    @Id
    private String username;
    private double balance;
}
