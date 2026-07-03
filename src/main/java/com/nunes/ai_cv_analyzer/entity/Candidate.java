package com.nunes.ai_cv_analyzer.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "candidates")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    private String email;

    private String phone;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "cv_file_path")
    private String cvFilePath;

    @Column(name = "extracted_text", columnDefinition = "TEXT")
    private String extractedText;

    public Candidate() {
    }

    public Candidate(Long id, String fullName, String email, String phone, LocalDateTime createdAt) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCvFilePath() {
        return cvFilePath;
    }

    public void setCvFilePath(String cvFilePath) {
        this.cvFilePath = cvFilePath;
    }

    public String getExtractedText() {
        return extractedText;
    }

    public void setExtractedText(String extractedText) {
        this.extractedText = extractedText;
    }

    @OneToOne(mappedBy = "candidate",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private CandidateAnalysis analysis;

    public CandidateAnalysis getAnalysis() {
        return analysis;
    }

    public void setAnalysis(CandidateAnalysis analysis) {
        this.analysis = analysis;
    }

    public String getDisplayName() {

        String[] parts = fullName.trim().split("\\s+");

        if (parts.length == 1) {
            return parts[0];
        }

        return parts[0] + " " + parts[parts.length - 1];
    }
}