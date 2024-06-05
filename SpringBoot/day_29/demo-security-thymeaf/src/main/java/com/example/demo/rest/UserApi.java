package com.example.demo.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserApi {
    @GetMapping("/read-profile/{id}")
    public ResponseEntity<?>readProfile(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }

    @PutMapping("/update-profile/{id}")
    public ResponseEntity<?> updateProfile(@PathVariable Integer id) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping("/update-password/{id}")
    public ResponseEntity<?> updatePassword(@PathVariable Integer id) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
