package com.example.Police.service;

import com.example.Police.dto.AssignmentRequest;
import com.example.Police.model.*;
import com.example.Police.repository.AssignmentRepository;
import com.example.Police.repository.PatrolRepository;
import com.example.Police.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private PatrolRepository patrolRepository;

    @Autowired
    private UserRepository userRepository;

    public Assignment assignOfficer(AssignmentRequest request, String shoEmail) {
        Patrol patrol = patrolRepository.findById(request.getPatrolId())
                .orElseThrow(() -> new RuntimeException("Patrol not found with id: " + request.getPatrolId()));

        User officer = userRepository.findByEmail(request.getOfficerEmail())
                .orElseThrow(() -> new RuntimeException("Officer not found with email: " + request.getOfficerEmail()));

        User sho = userRepository.findByEmail(shoEmail)
                .orElseThrow(() -> new RuntimeException("SHO not found"));

        if (assignmentRepository.existsByPatrolAndOfficer(patrol, officer)) {
            throw new RuntimeException("Officer " + officer.getName() + " is already assigned to this patrol.");
        }

        Assignment assignment = Assignment.builder()
                .patrol(patrol)
                .officer(officer)
                .assignedBy(sho)
                .status(AssignmentStatus.PENDING)
                .assignedAt(LocalDateTime.now())
                .build();

        return assignmentRepository.save(assignment);
    }

    public List<Assignment> getAssignmentsByPatrol(Long patrolId) {
        Patrol patrol = patrolRepository.findById(patrolId)
                .orElseThrow(() -> new RuntimeException("Patrol not found with id: " + patrolId));
        return assignmentRepository.findByPatrol(patrol);
    }

    public List<Assignment> getAssignmentsByOfficer(Long officerId) {
        User officer = userRepository.findById(officerId)
                .orElseThrow(() -> new RuntimeException("Officer not found with id: " + officerId));
        return assignmentRepository.findByOfficer(officer);
    }

    public Assignment acknowledgeAssignment(Long id, String officerEmail) {
        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assignment not found with id: " + id));

        if (!assignment.getOfficer().getEmail().equals(officerEmail)) {
            throw new RuntimeException("You can only acknowledge your own assignments.");
        }

        assignment.setStatus(AssignmentStatus.ACKNOWLEDGED);
        assignment.setAcknowledgedAt(LocalDateTime.now());
        return assignmentRepository.save(assignment);
    }

    public Assignment completeAssignment(Long id, String officerEmail, String remarks) {
        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assignment not found with id: " + id));

        if (!assignment.getOfficer().getEmail().equals(officerEmail)) {
            throw new RuntimeException("You can only complete your own assignments.");
        }

        assignment.setStatus(AssignmentStatus.COMPLETED);
        assignment.setCompletedAt(LocalDateTime.now());
        assignment.setRemarks(remarks);
        return assignmentRepository.save(assignment);
    }
}
