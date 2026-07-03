package com.nunes.ai_cv_analyzer.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

@Entity
@Table(name = "candidate_analysis")
public class CandidateAnalysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(
            name = "candidate_technical_skills",
            joinColumns = @JoinColumn(name = "candidate_analysis_id")
    )
    @Column(name = "skill")
    private List<String> technicalSkills;

    @ElementCollection
    @CollectionTable(
            name = "candidate_soft_skills",
            joinColumns = @JoinColumn(name = "candidate_analysis_id")
    )
    @Column(name = "skill")
    private List<String> softSkills;

    private String seniority;

    @ElementCollection
    @CollectionTable(
            name = "candidate_strengths",
            joinColumns = @JoinColumn(name = "candidate_analysis_id")
    )
    @Column(name = "strength")
    private List<String> strengths;

    @ElementCollection
    @CollectionTable(
            name = "candidate_weaknesses",
            joinColumns = @JoinColumn(name = "candidate_analysis_id")
    )
    @Column(name = "weakness")
    private List<String> weaknesses;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "candidate_id", nullable = false, unique = true)
    private Candidate candidate;

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

    public String getSeniority() {
        return seniority;
    }

    public void setSeniority(String seniority) {
        this.seniority = seniority;
    }

    public List<String> getStrengths() {
        return strengths;
    }

    public void setStrengths(List<String> strengths) {
        this.strengths = strengths;
    }

    public List<String> getWeaknesses() {
        return weaknesses;
    }

    public void setWeaknesses(List<String> weaknesses) {
        this.weaknesses = weaknesses;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }
}