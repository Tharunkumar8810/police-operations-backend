package com.example.Police.dto;

public class LocationRequest {
    private Long patrolId;
    private Double latitude;
    private Double longitude;

    public LocationRequest() {}

    public Long getPatrolId() { return patrolId; }
    public void setPatrolId(Long patrolId) { this.patrolId = patrolId; }

    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }
}