package com.example.Police.dto;

public class AssignmentRequest {
    private Long patrolId;
    private String officerEmail;

    public AssignmentRequest() {}

    public Long getPatrolId() { return patrolId; }
    public void setPatrolId(Long patrolId) { this.patrolId = patrolId; }

    public String getOfficerEmail() { return officerEmail; }
    public void setOfficerEmail(String officerEmail) { this.officerEmail = officerEmail; }
}