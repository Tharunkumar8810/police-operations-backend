package com.example.Police.model;

public enum AssignmentStatus {
    PENDING,        // assigned but officer hasn't acknowledged
    ACKNOWLEDGED,   // officer accepted the assignment
    IN_PROGRESS,    // officer is on ground
    COMPLETED,      // officer finished
    CANCELLED       // assignment was cancelled
}