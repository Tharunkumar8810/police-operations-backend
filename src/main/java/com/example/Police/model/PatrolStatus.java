package com.example.Police.model;

public enum PatrolStatus {
    PLANNED,     // created by SHO, not started yet
    ACTIVE,      // officers on ground
    COMPLETED,   // ended normally
    CANCELLED    // called off
}