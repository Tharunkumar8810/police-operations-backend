package com.example.Police.service;

import com.example.Police.dto.AlertRequest;
import com.example.Police.model.*;
import com.example.Police.repository.AlertRepository;
import com.example.Police.repository.PatrolRepository;
import com.example.Police.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AlertService {

    @Autowired
    private AlertRepository alertRepository;

    @Autowired
    private PatrolRepository patrolRepository;

    @Autowired
    private UserRepository userRepository;

    public Alert raiseAlert(AlertRequest request, String officerEmail) {
        Patrol patrol = patrolRepository.findById(request.getPatrolId())
                .orElseThrow(() -> new RuntimeException("Patrol not found with id: " + request.getPatrolId()));

        User officer = userRepository.findByEmail(officerEmail)
                .orElseThrow(() -> new RuntimeException("Officer not found"));

        Alert alert = Alert.builder()
                .patrol(patrol)
                .officer(officer)
                .type(request.getType())
                .message(request.getMessage())
                .status(AlertStatus.OPEN)
                .createdAt(LocalDateTime.now())
                .build();

        return alertRepository.save(alert);
    }

    public List<Alert> getAllAlerts() {
        return alertRepository.findAll();
    }

    public List<Alert> getAlertsByPatrol(Long patrolId) {
        Patrol patrol = patrolRepository.findById(patrolId)
                .orElseThrow(() -> new RuntimeException("Patrol not found with id: " + patrolId));
        return alertRepository.findByPatrol(patrol);
    }

    public Alert resolveAlert(Long id, String resolverEmail) {
        Alert alert = alertRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alert not found with id: " + id));

        User resolver = userRepository.findByEmail(resolverEmail)
                .orElseThrow(() -> new RuntimeException("Resolver not found"));

        alert.setStatus(AlertStatus.RESOLVED);
        alert.setResolvedAt(LocalDateTime.now());
        alert.setResolvedBy(resolver);

        return alertRepository.save(alert);
    }
}
