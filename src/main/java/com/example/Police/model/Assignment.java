package com.example.Police.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

import com.example.Police.common.AuditEntity;

@Entity
@Table(name = "police_assignments")

public class Assignment extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patrol_id", nullable = false)
    private Patrol patrol;        // which patrol this belongs to

    @ManyToOne
    @JoinColumn(name = "officer_id", nullable = false)
    private User officer;         // which officer is assigned

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AssignmentStatus status;

    @Column(nullable = false)
    private LocalDateTime assignedAt;

    @Column
    private LocalDateTime acknowledgedAt;  // when officer accepted

    @Column
    private LocalDateTime completedAt;     // when officer finished

    @Column
    private String remarks;               // officer's closing remarks

    @ManyToOne
    @JoinColumn(name = "assigned_by", nullable = false)
    private User assignedBy;             // SHO who assigned

    @PrePersist
    protected void onAssignmentCreate() {
        this.assignedAt = LocalDateTime.now();
        this.status = AssignmentStatus.PENDING;
    }

    public Assignment() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Patrol getPatrol() { return patrol; }
    public void setPatrol(Patrol patrol) { this.patrol = patrol; }

    public User getOfficer() { return officer; }
    public void setOfficer(User officer) { this.officer = officer; }

    public AssignmentStatus getStatus() { return status; }
    public void setStatus(AssignmentStatus status) { this.status = status; }

    public LocalDateTime getAssignedAt() { return assignedAt; }
    public void setAssignedAt(LocalDateTime assignedAt) { this.assignedAt = assignedAt; }

    public LocalDateTime getAcknowledgedAt() { return acknowledgedAt; }
    public void setAcknowledgedAt(LocalDateTime acknowledgedAt) { this.acknowledgedAt = acknowledgedAt; }

    public LocalDateTime getCompletedAt() { return completedAt; }
    public void setCompletedAt(LocalDateTime completedAt) { this.completedAt = completedAt; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }

    public User getAssignedBy() { return assignedBy; }
    public void setAssignedBy(User assignedBy) { this.assignedBy = assignedBy; }
}