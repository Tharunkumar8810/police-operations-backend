package com.example.Police.repository;

import com.example.Police.model.Role;
import com.example.Police.model.Status;
import com.example.Police.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    Optional<User> findByBadgeNumber(String badgeNumber);
    boolean existsByEmail(String email);
    boolean existsByBadgeNumber(String badgeNumber);
    List<User> findByRole(Role role);
    List<User> findByStatus(Status status);
}