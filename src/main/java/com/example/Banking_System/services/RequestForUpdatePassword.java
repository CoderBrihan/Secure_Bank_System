package com.example.Banking_System.services;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestForUpdatePassword {
    private String accountNo;
    private String password;
    private String newPassword;
}
