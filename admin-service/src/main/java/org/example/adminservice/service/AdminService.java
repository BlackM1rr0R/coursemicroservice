package org.example.adminservice.service;

import org.example.adminservice.model.Admin;
import org.example.adminservice.repository.AdminRepository;
import org.example.adminservice.role.AdminRole;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AdminService(AdminRepository adminRepository, BCryptPasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Admin yaratmaq
    public Admin createAdmin(Admin admin, AdminRole role) {
        if (adminRepository.findByEmail(admin.getEmail()).isPresent()) {
            throw new RuntimeException("Admin already exists with this email");
        }
        admin.setPassword(passwordEncoder.encode(admin.getPassword())); // Password encode
        admin.setRole(role); // Rol təyin et
        return adminRepository.save(admin);
    }

    // Login
    public Optional<Admin> login(String email, String password) {
        Optional<Admin> admin = adminRepository.findByEmail(email);
        if (admin.isPresent() && passwordEncoder.matches(password, admin.get().getPassword())) {
            return admin;
        }
        return Optional.empty();
    }
}
