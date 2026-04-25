package com.example.Police.repository;

import com.example.Police.model.Alert;
import com.example.Police.model.AlertStatus;
import com.example.Police.model.AlertType;
import com.example.Police.model.Patrol;
import com.example.Police.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {

    List<Alert> findByPatrol(Patrol patrol);
    List<Alert> findByOfficer(User officer);
    List<Alert> findByStatus(AlertStatus status);
    List<Alert> findByType(AlertType type);
    List<Alert> findByPatrolAndStatus(Patrol patrol, AlertStatus status);
}