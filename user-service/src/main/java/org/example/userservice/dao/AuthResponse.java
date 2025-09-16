package org.example.userservice.dao;

import org.example.userservice.model.User;

public class AuthResponse {
    private String token;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private String role;
    private String visa;
    private String visaStatus;

    public AuthResponse() {}

    public AuthResponse(String token, User user) {
        this.token = token;
        this.name = user.getName();
        this.surname = user.getSurname();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.role = user.getRole().toString();
        this.visa = user.getVisa().toString();
        this.visaStatus = user.getVisaStatus().toString();
    }

    // getters & setters
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getVisa() { return visa; }
    public void setVisa(String visa) { this.visa = visa; }

    public String getVisaStatus() { return visaStatus; }
    public void setVisaStatus(String visaStatus) { this.visaStatus = visaStatus; }
}
