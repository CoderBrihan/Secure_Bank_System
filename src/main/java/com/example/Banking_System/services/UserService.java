package com.example.Banking_System.services;

import com.example.Banking_System.entity.AccountEntity;
import com.example.Banking_System.entity.TransactionEntity;
import com.example.Banking_System.entity.UserEntity;
import com.example.Banking_System.repository.AccountRepository;
import com.example.Banking_System.repository.TransactionRepository;
import com.example.Banking_System.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.SecureRandom;
import java.util.List;


@Component
public class UserService {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountRepository accountRepository;

    public String generateTxnId() {
        long timestamp = System.currentTimeMillis();
        int random = new SecureRandom().nextInt(1_000_000);
        return "TXN" + timestamp + random;
    }


    ///   AMOUNT DEBITED FROM ACCOUNT

    public void debit(UserEntity user,int amount){

        String accountNo=user.getAccountNo();
        AccountEntity userAccount = accountRepository.findByAccountNo(accountNo);
        if(userAccount==null) return;
        if(userAccount.getAmount()<=amount) return;

        //Updating Account Details
        userAccount.setAmount(userAccount.getAmount()-amount);
       List<String> transactionList = userAccount.getTransactionList();
       String tranID = generateTxnId();
       transactionList.add(tranID);
       userAccount.setTransactionList(transactionList);
       accountRepository.save(userAccount);


       // Creating Transaction Entity
        TransactionEntity transactionEntity = new TransactionEntity();
       transactionEntity.setTransactionId(tranID);
       transactionEntity.setFromUserAccount(user.getAccountNo());
       transactionEntity.setToUserAccount(user.getAccountNo());
       transactionEntity.setTransactionAmount(amount);
       transactionRepository.save(transactionEntity);
       return;
    }


    ///  AMOUNT CREDITED TO ACCOUNT

    public void credit(UserEntity user,int amount){
        String accountNo=user.getAccountNo();
        AccountEntity userAccount = accountRepository.findByAccountNo(accountNo);
        if(userAccount==null) return;

        //Updating Account Details
        userAccount.setAmount(userAccount.getAmount()+amount);
        List<String> transactionList = userAccount.getTransactionList();
        String tranID = generateTxnId();
        transactionList.add(tranID);
        userAccount.setTransactionList(transactionList);
        accountRepository.save(userAccount);

        // Creating Transaction Entity
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setTransactionId(tranID);
        transactionEntity.setFromUserAccount(user.getAccountNo());
        transactionEntity.setToUserAccount(user.getAccountNo());
        transactionEntity.setTransactionAmount(amount);
        transactionRepository.save(transactionEntity);
        return;
    }


    ///  TRANSFER MONEY FROM ONE ACCOUNT TO ANOTHER
    public void transfer(String accountNo1,String accountNo2,String password,double amount){

        AccountEntity userAcc1=accountRepository.findByAccountNo(accountNo1);
        AccountEntity userAcc2=accountRepository.findByAccountNo(accountNo2);


        // Create Transaction ID
        String tranID=generateTxnId();

        //Updating first Account Details
        userAcc1.setAmount(userAcc1.getAmount()-amount);
        List<String> tranList1=userAcc1.getTransactionList();
        tranList1.add(tranID);
        userAcc1.setTransactionList(tranList1);
        accountRepository.save(userAcc1);

        //Updating second Account Details
        userAcc2.setAmount(userAcc2.getAmount()+amount);
        List<String> tranList2=userAcc2.getTransactionList();
        tranList2.add(tranID);
        userAcc2.setTransactionList(tranList2);
        accountRepository.save(userAcc2);

        //Creating Transaction Entity
        TransactionEntity transactionEntity=new TransactionEntity();
        transactionEntity.setTransactionId(tranID);
        transactionEntity.setTransactionAmount(amount);
        transactionEntity.setToUserAccount(accountNo2);
        transactionEntity.setFromUserAccount(accountNo1);
        transactionRepository.save(transactionEntity);

    }


    // Get USER
    public UserEntity getUser(String accountNo){

        return  userRepository.findByAccountNo(accountNo);
    }

    // Update PASSWORD
    public void updatePassword(String accountNumber,String newPassword){
        UserEntity user = userRepository.findByAccountNo(accountNumber);
        user.setPassword(newPassword);
        userRepository.save(user);
    }

}
