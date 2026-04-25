package com.example.Police.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "assignments")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Assignment {

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
    protected void onCreate() {
        this.assignedAt = LocalDateTime.now();
        this.status = AssignmentStatus.PENDING;
    }
}