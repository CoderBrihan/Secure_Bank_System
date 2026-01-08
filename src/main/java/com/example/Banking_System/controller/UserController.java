package com.example.Banking_System.controller;


import com.example.Banking_System.entity.AccountEntity;
import com.example.Banking_System.entity.UserEntity;
import com.example.Banking_System.repository.UserRepository;
import com.example.Banking_System.services.*;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private UserRepository userRepository;
    @PostMapping("/withdraw")
    public ResponseEntity<?> debitFromAccount(@RequestBody RequestForCreditDebit requestForDebit){

        AccountEntity accountEntity = accountService.getAccount(requestForDebit.getAccountNo());
        if(accountEntity==null) return new ResponseEntity<>("No user found", HttpStatus.NOT_FOUND);
        UserEntity userINdb=userService.getUser(requestForDebit.getAccountNo());
        if(userINdb==null) return new ResponseEntity<>("No user",HttpStatus.FORBIDDEN);
        if(!userINdb.getPassword().equals(requestForDebit.getPassword())) return  new ResponseEntity<>("Invalid user",HttpStatus.FORBIDDEN);
        double balance = accountService.checkAmount(requestForDebit.getAccountNo());
        if(requestForDebit.getAmount()>=balance) return new ResponseEntity<>("Insufficient balance",HttpStatus.FORBIDDEN);
        userService.debit(userINdb,requestForDebit.getAmount());
        String st = requestForDebit.getAmount() + " debited from account no:"+requestForDebit.getAccountNo();
        return new ResponseEntity<>(st,HttpStatus.ACCEPTED);
    }

    @GetMapping("/checkBalance")
    public ResponseEntity<?> check(@RequestBody RequestForCreditDebit userDetails){
        UserEntity userINdb=userService.getUser(userDetails.getAccountNo());
        if(userINdb==null) return new ResponseEntity<>("No user found", HttpStatus.NOT_FOUND);
        if(!userINdb.getPassword().equals(userDetails.getPassword())) return  new ResponseEntity<>("Invalid user",HttpStatus.FORBIDDEN);
        String st="Total balance:"+accountService.checkAmount(userDetails.getAccountNo());
        return new ResponseEntity<>(st,HttpStatus.FOUND);
    }

    @PostMapping("/deposit")
    public ResponseEntity<?> creditToAccount(@RequestBody RequestForCreditDebit requestForCredit){
        UserEntity userINdb=userService.getUser(requestForCredit.getAccountNo());
        if(userINdb==null) return new ResponseEntity<>("No user found", HttpStatus.NOT_FOUND);
        if(!userINdb.getPassword().equals(requestForCredit.getPassword())) return  new ResponseEntity<>("Invalid user",HttpStatus.FORBIDDEN);
        userService.credit(userINdb, requestForCredit.getAmount());
        String st = requestForCredit.getAmount() + " credited to account no:"+requestForCredit.getAccountNo();
        return new ResponseEntity<>(st,HttpStatus.ACCEPTED);
    }

    @PutMapping("/updatePassword")
    public ResponseEntity<?> updatePassword(@RequestBody RequestForUpdatePassword userDestails){
        UserEntity userINdb=userService.getUser(userDestails.getAccountNo());
        if(userINdb==null) return new ResponseEntity<>("No user found",HttpStatus.NOT_FOUND);
        if(!userINdb.getPassword().equals(userDestails.getPassword())) return  new ResponseEntity<>("Invalid user",HttpStatus.FORBIDDEN);
        userService.updatePassword(userDestails.getAccountNo(), userDestails.getNewPassword());
        return new ResponseEntity<>("Password updated",HttpStatus.ACCEPTED);
    }

    @GetMapping("/getDetails/{id}")
    public ResponseEntity<?> getUser(@PathVariable String id){
        UserEntity user=userService.getUser(id);
        if(user==null) return new ResponseEntity<>("No user",HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(user,HttpStatus.FOUND);
    }


    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(@RequestBody RequestForTransferMoney requestForTransferMoney){
        String accNo1= requestForTransferMoney.getAccNo1();
        String accNo2= requestForTransferMoney.getAccNo2();
        String password= requestForTransferMoney.getPassword();
        double amount= requestForTransferMoney.getAmount();
        if(userRepository.findByAccountNo(accNo1)==null || userRepository.findByAccountNo(accNo2)==null) return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);
        UserEntity user1=userService.getUser(accNo1);
        if(!user1.getPassword().equals(requestForTransferMoney.getPassword())) return new ResponseEntity<>("Invalid user",HttpStatus.FORBIDDEN);
        AccountEntity acc1=accountService.getAccount(accNo1);
        if(amount>= acc1.getAmount()) return new ResponseEntity<>("Insufficient balance",HttpStatus.FORBIDDEN);
        userService.transfer(accNo1,accNo2,password,amount);
        String st = "Your account:"+accNo1+" is debited by "+amount;
        return new ResponseEntity<>(st,HttpStatus.ACCEPTED);
    }
}
