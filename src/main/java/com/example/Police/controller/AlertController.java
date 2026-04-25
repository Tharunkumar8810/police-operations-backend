package com.example.Police.controller;

import com.example.Police.dto.AlertRequest;
import com.example.Police.model.Alert;
import com.example.Police.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
public class AlertController {

    @Autowired
    private AlertService alertService;

    @PostMapping
    public ResponseEntity<Alert> raiseAlert(
            @RequestBody AlertRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(alertService.raiseAlert(request, userDetails.getUsername()));
    }

    @GetMapping
    public ResponseEntity<List<Alert>> getAllAlerts() {
        return ResponseEntity.ok(alertService.getAllAlerts());
    }

    @GetMapping("/patrol/{patrolId}")
    public ResponseEntity<List<Alert>> getByPatrol(@PathVariable Long patrolId) {
        return ResponseEntity.ok(alertService.getAlertsByPatrol(patrolId));
    }

    @PatchMapping("/{id}/resolve")
    public ResponseEntity<Alert> resolveAlert(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(alertService.resolveAlert(id, userDetails.getUsername()));
    }
}
