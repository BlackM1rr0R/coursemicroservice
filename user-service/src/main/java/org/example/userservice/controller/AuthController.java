package org.example.userservice.controller;

import org.example.userservice.dao.AuthRequest;
import org.example.userservice.dao.AuthResponse;
import org.example.userservice.model.User;
import org.example.userservice.service.UserService;
import org.example.userservice.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        String result = userService.register(user);
        if(result == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already exists");
        return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        Optional<User> userOpt = userService.login(authRequest);
        if(userOpt.isEmpty()) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");

        User user = userOpt.get();
        String token = jwtUtil.generateToken(user.getEmail());
        return ResponseEntity.ok(new AuthResponse(token, user));
    }

    @GetMapping("/profile")
    public ResponseEntity<User> getMyProfile(@RequestHeader("X-User-Email") String email) {
        User user = userService.getMyProfile(email);
        return ResponseEntity.ok(user);
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateProfile(@RequestHeader("X-User-Email") String email,@RequestBody User updatedUser){
       try{
           User user = userService.updateProfile(email,updatedUser);
           return ResponseEntity.ok(user);
       }catch(RuntimeException e){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
