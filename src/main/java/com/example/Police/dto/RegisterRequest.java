package com.example.Police.dto;

import com.example.Police.model.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private String badgeNumber;
    private Role role; // SHO or OFFICER
}