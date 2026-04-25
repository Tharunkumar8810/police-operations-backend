package com.example.Police.controller;

import com.example.Police.model.Status;
import com.example.Police.model.User;
import com.example.Police.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/officers")
    public ResponseEntity<List<User>> getAllOfficers() {
        return ResponseEntity.ok(userService.getAllOfficers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<User> updateUserStatus(
            @PathVariable Long id,
            @RequestParam Status newStatus) {
        return ResponseEntity.ok(userService.updateUserStatus(id, newStatus));
    }
}
