package com.example.Banking_System.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection ="Bank_Details")
@Data
public class BankEntity {
    @Id
    private String bankName;
    private List<String> allIFSC=new ArrayList<>();
    private List<String> allBranch=new ArrayList<>();
}
