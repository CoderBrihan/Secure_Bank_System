package com.example.Banking_System.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "Account_Details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntity {
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    private String accountNo;
    private double amount;
    private  String firstName;
    private  String lastName;
    private String middleName;
    private List<String> transactionList = new ArrayList<>();

}
