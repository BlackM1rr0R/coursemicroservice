package org.example.userservice.mapping;

import org.example.userservice.dao.AuthResponse;
import org.example.userservice.dao.RegisterRequest;
import org.example.userservice.dao.RegisterResponse;
import org.example.userservice.model.User;
import org.example.userservice.roles.UserRole;
import org.example.userservice.roles.UserVisa;
import org.example.userservice.roles.UserVisaStatus;

public class UserMapping {
    public static User toEntity(RegisterRequest request) {
        User user = new User();
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setPhoneNumber(request.getPhoneNumber());
        if (request.getRole() != null) {
            user.setRole(UserRole.valueOf(request.getRole().toUpperCase()));
        } else {
            user.setRole(UserRole.USER);
        }

        if (request.getVisa() != null) {
            user.setVisa(UserVisa.valueOf(request.getVisa().toUpperCase()));
        }else{
            user.setVisa(UserVisa.WORKER);
        }

        if (request.getVisaStatus() != null) {
            user.setVisaStatus(UserVisaStatus.valueOf(request.getVisaStatus().toUpperCase()));
        }else{
            user.setVisaStatus(UserVisaStatus.PENDING);
        }
        return user;
    }
    public static AuthResponse toLoginResponse(User user) {
        AuthResponse response = new AuthResponse();
        response.setName(user.getName());
        response.setSurname(user.getSurname());
        response.setEmail(user.getEmail());
        response.setPhoneNumber(user.getPhoneNumber());
        response.setRole(user.getRole() != null ? user.getRole().name() : null);
        response.setVisa(user.getVisa() != null ? user.getVisa().name() : null);
        response.setVisaStatus(user.getVisaStatus() != null ? user.getVisaStatus().name() : null);
        return response;
    }
    public static RegisterResponse toRegisterResponse(User user) {
        RegisterResponse response = new RegisterResponse();
        response.setEmail(user.getEmail());
        response.setMessage("Registration successful");
        return response;
    }
}
