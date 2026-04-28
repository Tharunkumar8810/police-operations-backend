package com.example.Police.dto;

import com.example.Police.model.AlertType;
public class AlertRequest {
    private Long patrolId;
    private AlertType type;   // SOS, SUSPICIOUS_ACTIVITY, MEDICAL_EMERGENCY, etc.
    private String message;

    public AlertRequest() {}

    public Long getPatrolId() { return patrolId; }
    public void setPatrolId(Long patrolId) { this.patrolId = patrolId; }

    public AlertType getType() { return type; }
    public void setType(AlertType type) { this.type = type; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
