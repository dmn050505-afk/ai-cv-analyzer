package com.nunes.ai_cv_analyzer.dto;

public class MatchRankingDTO {

    private Long candidateId;
    private String candidateName;
    private double overallScore;

    public MatchRankingDTO() {}

    public Long getCandidateId() {
        return candidateId;
    }
    public void setCandidateId(Long candidateId) {
        this.candidateId = candidateId;
    }
    public String getCandidateName() {
        return candidateName;
    }
    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }
    public double getOverallScore() {
        return overallScore;
    }
    public void setOverallScore(double overallScore) {
        this.overallScore = overallScore;
    }

}