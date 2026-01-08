package com.example.Banking_System.services;

import com.example.Banking_System.entity.BranchEntity;
import com.example.Banking_System.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class RequestForCreateAccount {

    private BranchEntity branchEntity;
    private UserEntity userEntity;
}
