package com.nunes.ai_cv_analyzer.dto;

import java.util.List;

public class MatchResponseDTO {

    private Long candidateId;

    private String candidateName;

    private Long jobId;

    private double overallScore;

    private double technicalScore;

    private double softSkillScore;

    private double seniorityScore;

    private List<String> matchedTechnicalSkills;

    private List<String> missingTechnicalSkills;

    public MatchResponseDTO() {
    }

    public Long getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Long candidateId) {
        this.candidateId = candidateId;
    }

    public String getCandidateName() {return candidateName;}

    public void setCandidateName(String candidateName) {this.candidateName = candidateName;}

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public double getOverallScore() {
        return overallScore;
    }

    public void setOverallScore(double overallScore) {
        this.overallScore = overallScore;
    }

    public double getTechnicalScore() {
        return technicalScore;
    }

    public void setTechnicalScore(double technicalScore) {
        this.technicalScore = technicalScore;
    }

    public double getSoftSkillScore() {
        return softSkillScore;
    }

    public void setSoftSkillScore(double softSkillScore) {
        this.softSkillScore = softSkillScore;
    }

    public double getSeniorityScore() {
        return seniorityScore;
    }

    public void setSeniorityScore(double seniorityScore) {
        this.seniorityScore = seniorityScore;
    }

    public List<String> getMatchedTechnicalSkills() {
        return matchedTechnicalSkills;
    }

    public void setMatchedTechnicalSkills(List<String> matchedTechnicalSkills) {
        this.matchedTechnicalSkills = matchedTechnicalSkills;
    }

    public List<String> getMissingTechnicalSkills() {
        return missingTechnicalSkills;
    }

    public void setMissingTechnicalSkills(List<String> missingTechnicalSkills) {
        this.missingTechnicalSkills = missingTechnicalSkills;
    }
}