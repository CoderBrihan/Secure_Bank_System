package com.example.Banking_System.controller;

import com.example.Banking_System.entity.AccountEntity;
import com.example.Banking_System.entity.BranchEntity;
import com.example.Banking_System.entity.UserEntity;
import com.example.Banking_System.services.BranchService;
import com.example.Banking_System.services.RequestForCreateAccount;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController

@RequestMapping("/branch")
public class BranchCntroller {
    @Autowired
    private BranchService branchService;


    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@RequestBody RequestForCreateAccount requestForCreateAccount){
        if (requestForCreateAccount.getBranchEntity() == null || requestForCreateAccount.getUserEntity() == null) {
            throw new IllegalArgumentException("Branch or User data missing");
        }
        BranchEntity branchEntity=requestForCreateAccount.getBranchEntity();
        UserEntity newUser=requestForCreateAccount.getUserEntity();
        newUser.setBankName(branchEntity.getName());
        newUser.setIFSC(branchEntity.getIFSC());
        String accountNO=branchService.createAccount(branchEntity,newUser);
        String st = "Account created.Your account no:"+accountNO;
        return new ResponseEntity<>(st, HttpStatus.CREATED);
    }

    @GetMapping("/allUsers")
    public List<AccountEntity> getAll(){
        return  branchService.getAll();
    }

}


