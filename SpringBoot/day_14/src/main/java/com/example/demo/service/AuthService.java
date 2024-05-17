package com.example.demo.service;


import com.example.demo.entities.User;
import com.example.demo.model.enums.UserRole;
import com.example.demo.model.request.LoginRequest;
import com.example.demo.model.request.RegisterRequest;
import com.example.demo.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bcryptPasswordEncoder;
    private final HttpSession httpSession;

    public void login(LoginRequest loginRequest) {
        //Kiểm tra email
        User user =userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("Email is incorrect"));
        //Kiểm tra pass có khớp với pass trong database k
        if (!bcryptPasswordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Password is incorrect");
        }

        //Lưu thông tin user vào trong session để sử dụng ở các request tếp theo
        httpSession.setAttribute("user", user);
    }

    public User createUser(RegisterRequest registerRequest) {
        //Cần kiểm tra user đã tồn tại hay chưa
        if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()){
            throw new RuntimeException("Email is already in use");
        }
        //confirmPassword có trùng với password hay không
        if (registerRequest.getPassword() == null){
            throw new RuntimeException("Password is required");
        }

        //Lưu password vào database cần mã hóa password
        User user = User.builder()
                .name(registerRequest.getName())
                .email(registerRequest.getEmail())
                .password(bcryptPasswordEncoder.encode(registerRequest.getPassword()))
                .avatar("https://placehold.co/600x400?text=" +String.valueOf(registerRequest.getName().charAt(0)).toUpperCase())
                .createdAt(LocalDate.now())
                .updatedAt(LocalDate.now())
                .role(UserRole.USER)
                .build();
        userRepository.save(user);
        return user;
    }
}
