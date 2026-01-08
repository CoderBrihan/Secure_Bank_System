package com.example.Banking_System.services;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestForTransferMoney {
    private String accNo1;
    private String password;
    private double amount;
    private String accNo2;
}
