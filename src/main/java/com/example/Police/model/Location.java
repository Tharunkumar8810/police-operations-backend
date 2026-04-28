package com.example.Police.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

import com.example.Police.common.AuditEntity;

@Entity
@Table(name = "police_locations")

public class Location extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "officer_id", nullable = false)
    private User officer;          // which officer sent this location

    @ManyToOne
    @JoinColumn(name = "patrol_id", nullable = false)
    private Patrol patrol;         // which patrol this belongs to

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @PrePersist
    protected void onLocationCreate() {
        this.timestamp = LocalDateTime.now();
    }

    public Location() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getOfficer() { return officer; }
    public void setOfficer(User officer) { this.officer = officer; }

    public Patrol getPatrol() { return patrol; }
    public void setPatrol(Patrol patrol) { this.patrol = patrol; }

    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}