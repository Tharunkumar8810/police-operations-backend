package com.example.Police.security;

import com.example.Police.model.User;
import com.example.Police.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // We use email as the unique identifier for login
        User user = repo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Police officer not found with email: " + email));

        return new UserPrincipal(user);
    }
}