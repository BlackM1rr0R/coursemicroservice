package org.example.adminservice.dto;

import org.example.adminservice.role.AdminRole;

public class LoginResponse {
    private String token;
    private String email;
    private AdminRole role;

    public LoginResponse(String token, String email, AdminRole role) {
        this.token = token;
        this.email = email;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AdminRole getRole() {
        return role;
    }

    public void setRole(AdminRole role) {
        this.role = role;
    }
}
