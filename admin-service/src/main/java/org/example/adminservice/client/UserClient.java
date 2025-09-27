package org.example.adminservice.client;

import org.example.adminservice.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "user-service")
public interface UserClient {
    @PostMapping("/user/register")
    String registerUser(@RequestBody UserDTO userDTO);

    @GetMapping("/user/all")
    List<UserDTO> getAllUsers();
}
