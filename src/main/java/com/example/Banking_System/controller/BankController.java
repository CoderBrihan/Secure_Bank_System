package com.example.Banking_System.controller;


import com.example.Banking_System.entity.BankEntity;
import com.example.Banking_System.entity.BranchEntity;
import com.example.Banking_System.repository.BankRepository;
import com.example.Banking_System.services.BankService;
import com.example.Banking_System.services.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Bank")
public class BankController {

    @Autowired
    private BankService bankService;
    @Autowired
    private BranchService branchService;

    @PostMapping("/changeIFSC")
    public ResponseEntity<?> changeIFSC(@RequestBody BankEntity bankEntity,@RequestBody String oldIFSC,@RequestBody String newIFSC){
          bankService.changeIFSC(bankEntity,oldIFSC,newIFSC);
          return new ResponseEntity<>("IFSC Updated", HttpStatus.ACCEPTED);
    }
    @PostMapping("/chnageBranchName")
    public ResponseEntity<?> changeBranchName(@RequestBody BankEntity bankEntity,@RequestBody String oldName,@RequestBody String newName){
        bankService.changeBranchName(bankEntity,oldName,newName);
        return new ResponseEntity<>("Branch Name Updated",HttpStatus.ACCEPTED);
    }
    @PostMapping("/createBranch")
    public void createBranch(@RequestBody BranchEntity branchEntity){
        branchService.createBranch(branchEntity);
    }



}
