package com.example.Banking_System.services;

import com.example.Banking_System.entity.*;
import com.example.Banking_System.repository.*;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Component
public class BankService {

    @Autowired
    private BankRepository bankRepository;
    @Autowired
    private BranchRepository branchRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmployeeEntityRepository employeeEntityRepository;
    @Autowired
    private EmployeeDetailsRepository employeeDetailsRepository;

    public void changeIFSC(BankEntity bankEntity, String oldIFSC, String newIFSC){
        List<String> list = bankEntity.getAllIFSC();
        for(int i=0;i< list.size();i++){
            if(list.get(i).equals(oldIFSC)){
                list.set(i,newIFSC);
                break;
            }
        }
        bankEntity.setAllIFSC(list);
        bankRepository.save(bankEntity);
        BranchEntity branchEntity=branchRepository.findBranchByIFSC(oldIFSC);
        branchEntity.setIFSC(newIFSC);
        List<String> accList = branchEntity.getAccountNumbers();
        for(int i=0;i< accList.size();i++){
            UserEntity userEntity=userRepository.findByAccountNo(accList.get(i));
            userEntity.setIFSC(newIFSC);
            userRepository.save(userEntity);
        }
        branchRepository.save(branchEntity);
    }

    public void changeBranchName(BankEntity bankEntity,String oldBranchName,String newBranchName){
        List<String> list=bankEntity.getAllBranch();
        for(int i=0;i< list.size();i++){
            if(list.get(i).equals(oldBranchName)){
                list.set(i,newBranchName);
                break;
            }
        }
        bankEntity.setAllBranch(list);
        bankRepository.save(bankEntity);
        BranchEntity branchEntity=branchRepository.findBranchByName(oldBranchName);
        branchEntity.setName(newBranchName);
        branchRepository.save(branchEntity);
    }

    public String createEmp(@RequestBody RequestForEmployeeCreation employeeCreation){
        EmployeeDetailsEntity newEmp = new EmployeeDetailsEntity();
        newEmp.setFirstName(employeeCreation.getFirstName());
        newEmp.setLastName(employeeCreation.getLastName());
        newEmp.setMiddleName(employeeCreation.getMiddleName());
        newEmp.setJoinDate(employeeCreation.getJoinDate());
        newEmp.setCategory(employeeCreation.getCategory());
        newEmp.setSalary(employeeCreation.getSalary());
        newEmp.setPhoneNumber(employeeCreation.getPhoneNumber());

        EmployeeEntity newEmpEntity = new EmployeeEntity();
        newEmpEntity.setRole("ROLE_EMP");
        newEmpEntity.setUserName(employeeCreation.getUserName());
        employeeEntityRepository.save(newEmpEntity);
        String empId = String.valueOf(employeeEntityRepository.findByUsername(employeeCreation.getUserName()));
        newEmp.setEmployeeID(empId);
        employeeDetailsRepository.save(newEmp);
        return empId;
    }

}
