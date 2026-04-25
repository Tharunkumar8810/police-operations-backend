package com.example.Police.service;

import com.example.Police.model.Role;
import com.example.Police.model.Status;
import com.example.Police.model.User;
import com.example.Police.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllOfficers() {
        return userRepository.findByRole(Role.OFFICER);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public User updateUserStatus(Long id, Status newStatus) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        user.setStatus(newStatus);
        return userRepository.save(user);
    }
}
