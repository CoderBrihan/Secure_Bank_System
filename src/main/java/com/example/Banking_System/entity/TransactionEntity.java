package com.example.Banking_System.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection ="Transaction_Details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEntity {
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    private String transactionId;
    private String fromUserAccount;
    private String toUserAccount;
    private double transactionAmount;
}
