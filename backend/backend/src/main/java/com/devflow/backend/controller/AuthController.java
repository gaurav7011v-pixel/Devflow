package com.devflow.backend.controller;

import com.devflow.backend.dto.LoginRequest;
import com.devflow.backend.dto.LoginResponse;
import com.devflow.backend.dto.RegisterRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.devflow.backend.services.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class AuthController{

    private final UserService userService;
    public AuthController(UserService userService){
        this.userService=userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request){
        System.out.println("Inside register controller");
        userService.register(request);
       return ResponseEntity.ok("Registered");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse response= userService.login(request);
        return ResponseEntity.ok(response);
    }
}
