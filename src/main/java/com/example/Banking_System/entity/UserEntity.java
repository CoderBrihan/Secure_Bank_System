package com.example.Banking_System.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection ="User_Details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    private String accountNo;
    @NonNull
    private String firstName;
    private String middleName;
    private String lastName;
    private String Email;
    private String Password;
    private String Phone;
    private String BankName;
    @NonNull
    private  String IFSC;

}
