package com.example.Police.dto;

import java.time.LocalDateTime;

public class PatrolRequest {
    private String title;
    private String area;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String notes;

    public PatrolRequest() {}

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }

    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }

    public LocalDateTime getEndTime() { return endTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}