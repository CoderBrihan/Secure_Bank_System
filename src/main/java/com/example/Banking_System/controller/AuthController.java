package com.example.Banking_System.controller;
import com.example.Banking_System.entity.LoginRequestEntity;
import com.example.Banking_System.entity.LoginResponseEntity;
import com.example.Banking_System.security.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    private final AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<LoginResponseEntity> login(@RequestBody LoginRequestEntity loginRequestEntity){
        return new ResponseEntity<>(authService.login(loginRequestEntity), HttpStatus.OK);
    }


}
