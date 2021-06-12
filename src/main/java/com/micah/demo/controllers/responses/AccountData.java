package com.micah.demo.controllers.responses;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountData {
    private String username;
    private double balance;
}
