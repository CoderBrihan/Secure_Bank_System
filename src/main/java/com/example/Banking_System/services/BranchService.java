package com.example.Banking_System.services;

import com.example.Banking_System.entity.*;
import com.example.Banking_System.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


@Component
public class BranchService {

    @Autowired
    private BranchRepository branchRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private  AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private EmployeeDetailsRepository employeeDetailsRepository;
    @Autowired
    private AccountService accountService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private String ifsc="SBIN700012SBDEY";

    public String AccountNoCreater(){
        String accNo = "SBI" + System.currentTimeMillis()
                + ThreadLocalRandom.current().nextInt(100, 999);

        return accNo;
    }

    public String createAccount(BranchEntity branchEntity,UserEntity newUser){
        String acNo=AccountNoCreater();
        newUser.setAccountNo(acNo);
        String password= newUser.getPassword();
        newUser.setPassword(passwordEncoder.encode(password));
        userRepository.save(newUser);
        accountService.saveNewAccount(newUser);
        BranchEntity branchEntity1 = branchRepository.findBranchByName(branchEntity.getName());
        List<String> list = branchEntity1.getAccountNumbers();
        list.add(acNo);
        branchEntity1.setAccountNumbers(list);
        branchRepository.save(branchEntity1);
        return acNo;
    }


//    public void updateEmployee(EmployeeEntity employeeEntity,BranchEntity branchEntity){
//        ObjectId id = employeeEntity.getEmployeeID();
//        EmployeeEntity employee = employeeRepository.findById(id).orElse(null);
//        if(employee==null) return;
//        if(!employeeEntity.getCategory().equals("")){
//            employee.setCategory(employeeEntity.getCategory());
//        }
//        if(employeeEntity.getSalary()!=0){
//            employee.setSalary(employee.getSalary());
//        }
//        if(!employeeEntity.getPhoneNumber().equals("")){
//            employee.setPhoneNumber(employeeEntity.getPhoneNumber());
//        }
//    }

    public void createBranch(BranchEntity branch){
        try {
            branchRepository.save(branch);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUser(String accountNo,String password,String IFSC){
        UserEntity userInDB = userRepository.findByAccountNo(accountNo);
        AccountEntity accInDB = accountRepository.findByAccountNo(accountNo);
        BranchEntity branchInDB = branchRepository.findBranchByIFSC(IFSC);
        List<String> transactionList = accInDB.getTransactionList();
        for(int i=0;i< transactionList.size();i++){
            String transaction=transactionList.get(i);
            TransactionEntity tranEntity = transactionRepository.findByTransactionId(transaction);
            transactionRepository.delete(tranEntity);
        }
        accountRepository.delete(accInDB);
        userRepository.delete(userInDB);

    }

    public List<AccountEntity> getAll(){
        return accountRepository.findAll();
    }
}
