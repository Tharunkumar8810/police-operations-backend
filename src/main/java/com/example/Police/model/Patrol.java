package com.example.Police.model;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import java.util.List;

import com.example.Police.common.AuditEntity;

@Entity
@Table(name = "patrols")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Patrol extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String area;           // e.g. "Koramangala Zone 3"

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PatrolStatus status;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;        // SHO who created this patrol

    @JsonIgnore
    @OneToMany(mappedBy = "patrol", cascade = CascadeType.ALL)
    private List<Assignment> assignments;

    @Column
    private String notes;          // optional instructions

    @Column
    private LocalDateTime closedAt;

    @PrePersist
    protected void onPatrolCreate() {
        this.status = PatrolStatus.PLANNED;
    }
}