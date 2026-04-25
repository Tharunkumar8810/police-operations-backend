package com.example.Police.controller;

import com.example.Police.dto.PatrolRequest;
import com.example.Police.model.Patrol;
import com.example.Police.model.PatrolStatus;
import com.example.Police.service.PatrolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patrols")
public class PatrolController {

    @Autowired
    private PatrolService patrolService;

    @PostMapping
    public ResponseEntity<Patrol> createPatrol(
            @RequestBody PatrolRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(patrolService.createPatrol(request, userDetails.getUsername()));
    }

    @GetMapping
    public ResponseEntity<List<Patrol>> getAllPatrols() {
        return ResponseEntity.ok(patrolService.getAllPatrols());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patrol> getPatrolById(@PathVariable Long id) {
        return ResponseEntity.ok(patrolService.getPatrolById(id));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Patrol>> getByStatus(@PathVariable PatrolStatus status) {
        return ResponseEntity.ok(patrolService.getPatrolsByStatus(status));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Patrol> updateStatus(
            @PathVariable Long id,
            @RequestParam PatrolStatus newStatus) {
        return ResponseEntity.ok(patrolService.updatePatrolStatus(id, newStatus));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatrol(@PathVariable Long id) {
        patrolService.deletePatrol(id);
        return ResponseEntity.ok("Patrol deleted successfully");
    }
}
