package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    //Ai cx truy cập đuợc
    @GetMapping("/")
    public String getHome() {
        return "index";
    }

    //có role user mới truy cập được
    @GetMapping("/user")
    public String getUser() {
        return "user";
    }

    //có role admin và sale mới truy cập được
    @GetMapping("/dashboard")
    public String getAdmin() {
        return "dashboard";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

 /*   @GetMapping("/info1")
    public ResponseEntity<?> getInfo1() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(authentication);
    }

    @GetMapping("/info2")
    public ResponseEntity<?> getInfo2(Principal principal) {
        return ResponseEntity.ok(principal);
    }

    @GetMapping("/info3")
    public ResponseEntity<?> getInfo3(Authentication authentication) {
        return ResponseEntity.ok(authentication);
    }

    @GetMapping("/info4")
    public ResponseEntity<?> getInfo4(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        return ResponseEntity.ok(principal);
    }
//Cách 5 dùng interface , giống cách 1
    @GetMapping("/info5")
    public ResponseEntity<?> getInfo5() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(authentication);
    }*/
}
