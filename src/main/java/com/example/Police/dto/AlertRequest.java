package com.example.Police.dto;

import com.example.Police.model.AlertType;
import lombok.Data;

@Data
public class AlertRequest {
    private Long patrolId;
    private AlertType type;   // SOS, SUSPICIOUS_ACTIVITY, MEDICAL_EMERGENCY, etc.
    private String message;
}
