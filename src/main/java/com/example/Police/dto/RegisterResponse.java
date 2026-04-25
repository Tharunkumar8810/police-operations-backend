package com.example.Police.dto;

import com.example.Police.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponse {

    private Long id;
    private String name;
    private String email;
    private String badgeNumber;
    private Role role;
    private String message; // Optional: To send a "Registration Successful" note

}