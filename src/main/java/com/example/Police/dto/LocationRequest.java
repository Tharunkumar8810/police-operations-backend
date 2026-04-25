package com.example.Police.dto;

import lombok.Data;

@Data
public class LocationRequest {
    private Long patrolId;
    private Double latitude;
    private Double longitude;
}