package com.devflow.backend.services;

import com.devflow.backend.dto.LoginRequest;
import com.devflow.backend.dto.LoginResponse;
import com.devflow.backend.dto.RegisterRequest;
import com.devflow.backend.entity.Role;
import com.devflow.backend.entity.User;
import com.devflow.backend.exception.EmailAlreadyExistsException;
import com.devflow.backend.exception.InvalidCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.devflow.backend.repository.UserRepository;


import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

   private final UserRepository userRepository;
   private final PasswordEncoder passwordEncoder;
   private final JwtService jwtService;

   public UserServiceImpl(UserRepository userRepository,
                   PasswordEncoder passwordEncoder,
                    JwtService jwtService){
       this.userRepository=userRepository;
       this.passwordEncoder=passwordEncoder;
       this.jwtService=jwtService;
   }

    @Override
    public String register(RegisterRequest request){

        if(userRepository.existsByEmail(request.getEmail())){
            throw new EmailAlreadyExistsException("Email already exists");
        }

            User user=new User();
            user.setName(request.getName());
            user.setEmail(request.getEmail());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setRole(Role.USER);
            user.setCreatedAt(LocalDateTime.now());
            user.setUpdatedAt(LocalDateTime.now());
            user.setProfileImage(null);
            userRepository.save(user);

            return "registered successfully";

    }

    @Override
    public LoginResponse login(LoginRequest request) {
        User user=userRepository.findByEmail(request.getEmail())
                .orElseThrow(()->
                        new InvalidCredentialsException("Invalid Email or Password"));

        if (!passwordEncoder.matches(request.getPassword(),
                user.getPassword()
        )){
           throw new InvalidCredentialsException("Invalid Email or Password");
        }
        String token=jwtService.generateToken(user.getEmail());
        LoginResponse response=new LoginResponse();
        response.setMessage("Welcome to DevFlow");
        response.setToken(token);
        return response;
    }
}
