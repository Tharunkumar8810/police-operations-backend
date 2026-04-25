package com.example.Police.model;

public enum AlertType {
    MISSED_CHECKIN,     // officer didn't send location for too long
    SOS,                // officer triggered emergency
    OFF_ROUTE,          // officer went outside patrol area
    PATROL_DELAYED,     // patrol didn't start on time
    MANUAL              // SHO/Supervisor raised manually
}