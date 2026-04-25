package com.example.Police.model;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "patrols")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Patrol {

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

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime closedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.status = PatrolStatus.PLANNED;
    }
}