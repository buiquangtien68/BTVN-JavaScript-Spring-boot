package com.example.demo.rest;

import com.example.demo.entities.User;
import com.example.demo.model.request.LoginRequest;
import com.example.demo.model.request.RegisterRequest;
import com.example.demo.model.request.UpdatePasswordRequest;
import com.example.demo.model.request.UpdateProfileUserRequest;
import com.example.demo.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthApi {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@Valid @RequestBody RegisterRequest registerRequest) {
        User user = authService.createUser(registerRequest);
        return new ResponseEntity<>(user, HttpStatus.CREATED); //201
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        authService.login(loginRequest);
        return ResponseEntity.ok("Logged in successfully");
    }

    @PutMapping("/update-profile/{id}")
    public ResponseEntity<?> updateProfile(@Valid @RequestBody UpdateProfileUserRequest updateProfileUserRequest, @Valid @PathVariable Integer id) {
        User user = authService.updateProfile(updateProfileUserRequest,id);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    @PutMapping("/update-password/{id}")
    public ResponseEntity<?> updatePassword(@Valid @RequestBody UpdatePasswordRequest updatePasswordRequest, @Valid @PathVariable Integer id) {
        User user = authService.updatePassword(updatePasswordRequest,id);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        authService.logout();
        return ResponseEntity.ok("Logged out successfully");
    }


}
