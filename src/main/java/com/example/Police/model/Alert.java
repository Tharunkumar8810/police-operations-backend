package com.example.Police.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "alerts")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Alert {

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

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime resolvedAt;

    @ManyToOne
    @JoinColumn(name = "resolved_by")
    private User resolvedBy;        // supervisor who resolved the alert

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.status = AlertStatus.OPEN;
    }
}