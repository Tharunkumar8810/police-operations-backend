package com.example.Police.model;

public enum AlertStatus {
    OPEN,       // alert raised, not yet handled
    RESOLVED,   // supervisor resolved it
    IGNORED     // supervisor chose to ignore
}