package com.example.Banking_System.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDetailsEntity {
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    private String EmployeeID;
    private String firstName;
    private String MiddleName;
    private String LastName;
    private LocalDateTime joinDate;
    private String category;
    private double salary;
    private  String phoneNumber;
}
