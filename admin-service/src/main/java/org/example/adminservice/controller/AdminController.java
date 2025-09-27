package org.example.adminservice.controller;

import org.example.adminservice.dto.LoginResponse;
import org.example.adminservice.jwt.JwtUtil;
import org.example.adminservice.model.Admin;
import org.example.adminservice.role.AdminRole;
import org.example.adminservice.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final JwtUtil jwtUtil;

    public AdminController(AdminService adminService, JwtUtil jwtUtil) {
        this.adminService = adminService;
        this.jwtUtil = jwtUtil;
    }

    // Admin yaratmaq (yalnız ADMIN rolunda)
    @PostMapping("/create")
    public ResponseEntity<?> createAdmin(@RequestHeader(value = "X-User-Role", required = false) String roleHeader,
                                         @RequestBody Admin admin) {

        if (roleHeader != null && !"ADMIN".equals(roleHeader)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied: only ADMIN can create admins");
        }
        if (admin.getRole() != AdminRole.ADMIN && admin.getRole() != AdminRole.MANAGER) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Role must be ADMIN or MANAGER");
        }
        Admin savedAdmin = adminService.createAdmin(admin, admin.getRole());
        return ResponseEntity.ok(savedAdmin);
    }

    // Login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Admin admin) {
        Optional<Admin> loggedIn = adminService.login(admin.getEmail(), admin.getPassword());

        if (loggedIn.isPresent()) {
            String token = jwtUtil.generateToken(loggedIn.get().getEmail(), loggedIn.get().getRole());
            // JSON olaraq token, email və role qaytarırıq
            return ResponseEntity.ok(new LoginResponse(token, loggedIn.get().getEmail(), loggedIn.get().getRole()));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

}