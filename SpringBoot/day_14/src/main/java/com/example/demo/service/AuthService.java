package com.example.demo.service;


import com.example.demo.entities.User;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.enums.UserRole;
import com.example.demo.model.request.LoginRequest;
import com.example.demo.model.request.RegisterRequest;
import com.example.demo.model.request.UpdatePasswordRequest;
import com.example.demo.model.request.UpdateProfileUserRequest;
import com.example.demo.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bcryptPasswordEncoder;
    private final HttpSession httpSession;

    public void login(LoginRequest loginRequest) {
        //Kiểm tra email
        User user =userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new BadRequestException("Email is incorrect"));
        //Kiểm tra pass có khớp với pass trong database k
        if (!bcryptPasswordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new BadRequestException("Password is incorrect");
        }

        //Lưu thông tin user vào trong session để sử dụng ở các request tếp theo
        httpSession.setAttribute("user", user);
    }

    public User createUser(RegisterRequest registerRequest) {
        //Cần kiểm tra user đã tồn tại hay chưa
        if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()){
            throw new BadRequestException("Email is already in use");
        }
        //kiểm tra đã điền mật khẩu chưa
        if (registerRequest.getPassword() == null){
            throw new BadRequestException("Password is required");
        }
        if (!registerRequest.getConfirmPassword().equals(registerRequest.getPassword())){
            throw new BadRequestException("Passwords do not match");
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

    public User updateProfile(UpdateProfileUserRequest updateProfileUserRequest, Integer id) {
        //Kiểm tra user này có tồn tại hay ko
        User user = (User) httpSession.getAttribute("user");
        if (!Objects.equals(user.getId(), id)){
            throw new ResourceNotFoundException("User not found");
        }

        user.setName(updateProfileUserRequest.getName());
        user.setUpdatedAt(LocalDate.now());
        userRepository.save(user);
        return user;
    }

    public User updatePassword(UpdatePasswordRequest updatePasswordRequest, Integer id) {
        //Kiểm tra user này có tồn tại hay ko
        User user = (User) httpSession.getAttribute("user");
        if (!Objects.equals(user.getId(), id)){
            throw new ResourceNotFoundException("User not found");
        }

        if (!bcryptPasswordEncoder.matches(updatePasswordRequest.getOldPassword(), user.getPassword())) {
            throw new BadRequestException("Mật khẩu cũ sai");
        }
        if (!updatePasswordRequest.getNewPassword().equals(updatePasswordRequest.getConfirmPassword())){
            throw new BadRequestException("Mật khẩu mới và mật khẩu confirm khác nhau");
        }

        if (updatePasswordRequest.getNewPassword().equals(updatePasswordRequest.getOldPassword())){
            throw new BadRequestException("Mật khẩu mới và mật khẩu cũ giống nhau");
        }
        user.setPassword(bcryptPasswordEncoder.encode(updatePasswordRequest.getNewPassword()));
        userRepository.save(user);
        return user;
    }

    public void logout() {
        httpSession.setAttribute("user", null);
    }


}
