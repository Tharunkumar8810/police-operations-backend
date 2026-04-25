package com.example.Police.controller;

import com.example.Police.dto.AssignmentRequest;
import com.example.Police.model.Assignment;
import com.example.Police.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    @PostMapping
    public ResponseEntity<Assignment> assignOfficer(
            @RequestBody AssignmentRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(assignmentService.assignOfficer(request, userDetails.getUsername()));
    }

    @GetMapping("/patrol/{patrolId}")
    public ResponseEntity<List<Assignment>> getByPatrol(@PathVariable Long patrolId) {
        return ResponseEntity.ok(assignmentService.getAssignmentsByPatrol(patrolId));
    }

    @GetMapping("/officer/{officerId}")
    public ResponseEntity<List<Assignment>> getByOfficer(@PathVariable Long officerId) {
        return ResponseEntity.ok(assignmentService.getAssignmentsByOfficer(officerId));
    }

    @PatchMapping("/{id}/acknowledge")
    public ResponseEntity<Assignment> acknowledge(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(assignmentService.acknowledgeAssignment(id, userDetails.getUsername()));
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<Assignment> complete(
            @PathVariable Long id,
            @RequestParam(required = false, defaultValue = "") String remarks,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(assignmentService.completeAssignment(id, userDetails.getUsername(), remarks));
    }
}
