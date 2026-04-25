package com.example.Police.controller;

import com.example.Police.dto.LocationRequest;
import com.example.Police.model.Location;
import com.example.Police.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @PostMapping("/update")
    public ResponseEntity<Location> updateLocation(
            @RequestBody LocationRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(locationService.updateLocation(request, userDetails.getUsername()));
    }

    @GetMapping("/patrol/{patrolId}")
    public ResponseEntity<List<Location>> getByPatrol(@PathVariable Long patrolId) {
        return ResponseEntity.ok(locationService.getLocationsByPatrol(patrolId));
    }

    @GetMapping("/officer/{officerId}/patrol/{patrolId}/latest")
    public ResponseEntity<Location> getLatest(
            @PathVariable Long officerId,
            @PathVariable Long patrolId) {
        return ResponseEntity.ok(locationService.getLatestLocationByOfficer(officerId, patrolId));
    }
}
