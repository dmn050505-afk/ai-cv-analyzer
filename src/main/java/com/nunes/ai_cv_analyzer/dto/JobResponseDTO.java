package com.nunes.ai_cv_analyzer.dto;

import com.nunes.ai_cv_analyzer.entity.JobStatus;
import com.nunes.ai_cv_analyzer.entity.Seniority;

import java.time.LocalDateTime;
import java.util.List;

public class JobResponseDTO {

    private Long id;

    private String title;

    private String description;

    private Seniority requiredSeniority;

    private List<String> technicalSkills;

    private List<String> softSkills;

    private JobStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public JobResponseDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Seniority getRequiredSeniority() {
        return requiredSeniority;
    }

    public void setRequiredSeniority(Seniority requiredSeniority) {
        this.requiredSeniority = requiredSeniority;
    }

    public List<String> getTechnicalSkills() {
        return technicalSkills;
    }

    public void setTechnicalSkills(List<String> technicalSkills) {
        this.technicalSkills = technicalSkills;
    }

    public List<String> getSoftSkills() {
        return softSkills;
    }

    public void setSoftSkills(List<String> softSkills) {
        this.softSkills = softSkills;
    }

    public JobStatus getStatus() {
        return status;
    }

    public void setStatus(JobStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}