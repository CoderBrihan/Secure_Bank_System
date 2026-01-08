package com.example.Banking_System.security;


import com.example.Banking_System.entity.*;
import com.example.Banking_System.services.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    @Autowired
    private final AuthenticationManager authenticationManager;
    @Autowired
    private final AuthUtil authUtil;


    public LoginResponseEntity login(LoginRequestEntity loginRequestEntity){
        Authentication authentication = authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(loginRequestEntity.getEmployeeId(),loginRequestEntity.getPassword())
        );

        EmployeeEntity employeeEntity = (EmployeeEntity) authentication.getPrincipal();
        String token = authUtil.generateAccessToken(employeeEntity);
        return new LoginResponseEntity(token,employeeEntity.getEmployeeID());
    }


}
