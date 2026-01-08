package com.example.Banking_System.services;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestForCreditDebit {
    private String accountNo;
    private String password;
    private int amount;
}