package com.example.Police.repository;

import com.example.Police.model.Patrol;
import com.example.Police.model.PatrolStatus;
import com.example.Police.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PatrolRepository extends JpaRepository<Patrol, Long> {

    List<Patrol> findByStatus(PatrolStatus status);
    List<Patrol> findByCreatedBy(User createdBy);
    List<Patrol> findByArea(String area);
}