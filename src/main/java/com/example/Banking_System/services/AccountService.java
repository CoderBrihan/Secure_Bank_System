package com.example.Banking_System.services;

import com.example.Banking_System.entity.AccountEntity;
import com.example.Banking_System.entity.UserEntity;
import com.example.Banking_System.repository.AccountRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountService {


    @Autowired
    private AccountRepository accountRepository;


    public double checkAmount(String accountNo) {
        AccountEntity ac = accountRepository.findByAccountNo(accountNo);
        return ac.getAmount();
    }

    public void saveNewAccount(UserEntity user){
        AccountEntity accountEntity=new AccountEntity();
        accountEntity.setAccountNo(user.getAccountNo());
        accountEntity.setFirstName(user.getFirstName());
        accountEntity.setMiddleName(user.getMiddleName());
        accountEntity.setLastName(user.getLastName());
        accountEntity.setAmount(0);
        accountRepository.save(accountEntity);
    }

    public AccountEntity getAccount(String accountNo){

        return accountRepository.findByAccountNo(accountNo);
    }
}
