package org.example.userservice.service;

import org.example.userservice.dao.AuthRequest;
import org.example.userservice.model.User;
import org.example.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String register(User user) {
        if(userRepository.findByEmail(user.getEmail()).isPresent()) {
            return null;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User registered successfully";
    }

    public Optional<User> login(AuthRequest authRequest) {
        Optional<User> userOptional = userRepository.findByEmail(authRequest.getEmail());
        if(userOptional.isPresent()) {
            User user = userOptional.get();
            if(passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    public User getMyProfile(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User updateProfile(String email,User updatedUser) {
        User user=userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if(updatedUser.getName()!=null) user.setName(updatedUser.getName());
        if(updatedUser.getSurname()!=null) user.setSurname(updatedUser.getSurname());
        if(updatedUser.getPhoneNumber()!=null) user.setPhoneNumber(updatedUser.getPhoneNumber());
        if(updatedUser.getEmail()!=null) user.setEmail(updatedUser.getEmail());
        if(updatedUser.getPassword()!=null) user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
