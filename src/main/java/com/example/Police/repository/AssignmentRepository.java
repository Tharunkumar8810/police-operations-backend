package com.example.Police.repository;

import com.example.Police.model.Assignment;
import com.example.Police.model.AssignmentStatus;
import com.example.Police.model.Patrol;
import com.example.Police.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

    List<Assignment> findByPatrol(Patrol patrol);
    List<Assignment> findByOfficer(User officer);
    List<Assignment> findByStatus(AssignmentStatus status);
    List<Assignment> findByPatrolAndStatus(Patrol patrol, AssignmentStatus status);
    boolean existsByPatrolAndOfficer(Patrol patrol, User officer);
}