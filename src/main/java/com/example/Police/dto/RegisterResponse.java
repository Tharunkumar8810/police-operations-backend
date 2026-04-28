package com.example.Police.dto;

import com.example.Police.model.Role;

public class RegisterResponse {

    private Long id;
    private String name;
    private String email;
    private String badgeNumber;
    private Role role;
    private String message; // Optional: To send a "Registration Successful" note

    public RegisterResponse() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getBadgeNumber() { return badgeNumber; }
    public void setBadgeNumber(String badgeNumber) { this.badgeNumber = badgeNumber; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}