package com.example.Police.service;

import com.example.Police.dto.LocationRequest;
import com.example.Police.model.*;
import com.example.Police.repository.LocationRepository;
import com.example.Police.repository.PatrolRepository;
import com.example.Police.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private PatrolRepository patrolRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public Location updateLocation(LocationRequest request, String officerEmail) {
        User officer = userRepository.findByEmail(officerEmail)
                .orElseThrow(() -> new RuntimeException("Officer not found"));

        Patrol patrol = patrolRepository.findById(request.getPatrolId())
                .orElseThrow(() -> new RuntimeException("Patrol not found with id: " + request.getPatrolId()));

        Location location = Location.builder()
                .officer(officer)
                .patrol(patrol)
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .timestamp(LocalDateTime.now())
                .build();

        Location saved = locationRepository.save(location);

        messagingTemplate.convertAndSend("/topic/patrol-" + patrol.getId(), saved);

        return saved;
    }

    public List<Location> getLocationsByPatrol(Long patrolId) {
        Patrol patrol = patrolRepository.findById(patrolId)
                .orElseThrow(() -> new RuntimeException("Patrol not found with id: " + patrolId));
        return locationRepository.findByPatrolOrderByTimestampDesc(patrol);
    }

    public Location getLatestLocationByOfficer(Long officerId, Long patrolId) {
        User officer = userRepository.findById(officerId)
                .orElseThrow(() -> new RuntimeException("Officer not found with id: " + officerId));

        Patrol patrol = patrolRepository.findById(patrolId)
                .orElseThrow(() -> new RuntimeException("Patrol not found with id: " + patrolId));

        return locationRepository
                .findTopByOfficerAndPatrolOrderByTimestampDesc(officer, patrol)
                .orElseThrow(() -> new RuntimeException("No location data found for this officer in this patrol."));
    }
}
