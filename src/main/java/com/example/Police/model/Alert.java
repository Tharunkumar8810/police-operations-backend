package com.example.Police.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

import com.example.Police.common.AuditEntity;

@Entity
@Table(name = "police_alerts")

public class Alert extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patrol_id", nullable = false)
    private Patrol patrol;          // which patrol triggered this alert

    @ManyToOne
    @JoinColumn(name = "officer_id", nullable = false)
    private User officer;           // which officer triggered this alert

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AlertType type;

    @Column(nullable = false)
    private String message;         // human readable alert message

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AlertStatus status;

    @Column
    private LocalDateTime resolvedAt;

    @ManyToOne
    @JoinColumn(name = "resolved_by")
    private User resolvedBy;        // supervisor who resolved the alert

    @PrePersist
    protected void onAlertCreate() {
        this.status = AlertStatus.OPEN;
    }

    public Alert() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Patrol getPatrol() { return patrol; }
    public void setPatrol(Patrol patrol) { this.patrol = patrol; }

    public User getOfficer() { return officer; }
    public void setOfficer(User officer) { this.officer = officer; }

    public AlertType getType() { return type; }
    public void setType(AlertType type) { this.type = type; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public AlertStatus getStatus() { return status; }
    public void setStatus(AlertStatus status) { this.status = status; }

    public LocalDateTime getResolvedAt() { return resolvedAt; }
    public void setResolvedAt(LocalDateTime resolvedAt) { this.resolvedAt = resolvedAt; }

    public User getResolvedBy() { return resolvedBy; }
    public void setResolvedBy(User resolvedBy) { this.resolvedBy = resolvedBy; }
}