package com.example.Police.dto;

import com.example.Police.model.Role;

public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private String badgeNumber;
    private Role role; // SHO or OFFICER

    public RegisterRequest() {}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getBadgeNumber() { return badgeNumber; }
    public void setBadgeNumber(String badgeNumber) { this.badgeNumber = badgeNumber; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
}