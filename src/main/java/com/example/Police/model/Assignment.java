package com.example.Police.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

import com.example.Police.common.AuditEntity;

@Entity
@Table(name = "police_assignments")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
}