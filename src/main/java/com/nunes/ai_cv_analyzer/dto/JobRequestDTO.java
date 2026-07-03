package com.nunes.ai_cv_analyzer.dto;

import com.nunes.ai_cv_analyzer.entity.JobStatus;
import com.nunes.ai_cv_analyzer.entity.Seniority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class JobRequestDTO {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Required seniority is required")
    private Seniority requiredSeniority;

    @NotEmpty(message = "At least one technical skill is required")
    private List<String> technicalSkills;

    private List<String> softSkills;

    @NotNull(message = "Status is required")
    private JobStatus status;

    public JobRequestDTO() {
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
}