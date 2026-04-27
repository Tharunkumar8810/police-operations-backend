package com.example.Police.service;

import com.example.Police.dto.PatrolRequest;
import com.example.Police.model.*;
import com.example.Police.repository.PatrolRepository;
import com.example.Police.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PatrolService {

    @Autowired
    private PatrolRepository patrolRepository;

    @Autowired
    private UserRepository userRepository;

    public Patrol createPatrol(PatrolRequest request, String shoEmail) {
        User sho = userRepository.findByEmail(shoEmail)
                .orElseThrow(() -> new RuntimeException("SHO not found"));

        Patrol patrol = Patrol.builder()
                .title(request.getTitle())
                .area(request.getArea())
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .notes(request.getNotes())
                .status(PatrolStatus.PLANNED)
                .createdBy(sho)
                .build();

        return patrolRepository.save(patrol);
    }

    public List<Patrol> getAllPatrols() {
        return patrolRepository.findAll();
    }

    public Patrol getPatrolById(Long id) {
        return patrolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patrol not found with id: " + id));
    }

    public List<Patrol> getPatrolsByStatus(PatrolStatus status) {
        return patrolRepository.findByStatus(status);
    }

    public Patrol updatePatrolStatus(Long id, PatrolStatus newStatus) {
        Patrol patrol = patrolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patrol not found with id: " + id));

        patrol.setStatus(newStatus);
        if (newStatus == PatrolStatus.COMPLETED || newStatus == PatrolStatus.CANCELLED) {
            patrol.setClosedAt(LocalDateTime.now());
        }
        return patrolRepository.save(patrol);
    }

    public void deletePatrol(Long id) {
        if (!patrolRepository.existsById(id)) {
            throw new RuntimeException("Patrol not found with id: " + id);
        }
        patrolRepository.deleteById(id);
    }
}
