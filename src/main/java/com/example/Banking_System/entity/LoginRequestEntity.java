package com.example.Banking_System.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestEntity {
    private ObjectId employeeId;
    private String password;
}
