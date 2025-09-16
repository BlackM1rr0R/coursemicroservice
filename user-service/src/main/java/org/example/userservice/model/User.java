package org.example.userservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.userservice.roles.UserRole;
import org.example.userservice.roles.UserVisa;
import org.example.userservice.roles.UserVisaStatus;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "all_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @Enumerated(EnumType.STRING)
    private UserVisa visa;
    @Enumerated(EnumType.STRING)
    private UserVisaStatus visaStatus;
















    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public UserVisa getVisa() {
        return visa;
    }

    public void setVisa(UserVisa visa) {
        this.visa = visa;
    }

    public UserVisaStatus getVisaStatus() {
        return visaStatus;
    }

    public void setVisaStatus(UserVisaStatus visaStatus) {
        this.visaStatus = visaStatus;
    }


}
