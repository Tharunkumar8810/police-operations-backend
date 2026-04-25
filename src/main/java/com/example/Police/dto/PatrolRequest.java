package com.example.Police.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PatrolRequest {
    private String title;
    private String area;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String notes;
}