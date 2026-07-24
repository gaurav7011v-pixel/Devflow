package com.devflow.backend.services;

import com.devflow.backend.dto.LoginRequest;
import com.devflow.backend.dto.LoginResponse;
import com.devflow.backend.dto.RegisterRequest;

public interface UserService {
    String register(RegisterRequest request);
    LoginResponse login(LoginRequest request);
}
