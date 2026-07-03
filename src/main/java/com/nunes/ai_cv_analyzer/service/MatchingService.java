package com.nunes.ai_cv_analyzer.service;

import com.nunes.ai_cv_analyzer.dto.MatchRankingDTO;
import com.nunes.ai_cv_analyzer.dto.MatchResponseDTO;
import com.nunes.ai_cv_analyzer.entity.CandidateAnalysis;
import com.nunes.ai_cv_analyzer.entity.Job;
import com.nunes.ai_cv_analyzer.repository.CandidateAnalysisRepository;
import com.nunes.ai_cv_analyzer.repository.JobRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class MatchingService {

    private final CandidateAnalysisRepository candidateAnalysisRepository;
    private final JobRepository jobRepository;

    public MatchingService(CandidateAnalysisRepository candidateAnalysisRepository,
                           JobRepository jobRepository) {

        this.candidateAnalysisRepository = candidateAnalysisRepository;
        this.jobRepository = jobRepository;
    }

    public MatchResponseDTO match(Long candidateId, Long jobId) {

        CandidateAnalysis analysis = candidateAnalysisRepository
                .findByCandidateId(candidateId)
                .orElseThrow(() -> new EntityNotFoundException("Candidate analysis not found"));

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new EntityNotFoundException("Job not found"));

        MatchResponseDTO response = new MatchResponseDTO();

        response.setCandidateId(candidateId);
        response.setCandidateName(
                analysis.getCandidate().getDisplayName());
        response.setJobId(jobId);

        double technicalScore =
                calculateTechnicalScore(analysis, job, response);

        response.setTechnicalScore(round(technicalScore));
        double softSkillScore =
                calculateSoftSkillScore(analysis, job);

        response.setSoftSkillScore(round(softSkillScore));

        double seniorityScore =
                calculateSeniorityScore(analysis, job);

        response.setSeniorityScore(round(seniorityScore));

        double overallScore =
                calculateOverallScore(
                        technicalScore,
                        softSkillScore,
                        seniorityScore);

        response.setOverallScore(round(overallScore));

        return response;
    }

    private double calculateTechnicalScore(
            CandidateAnalysis analysis,
            Job job,
            MatchResponseDTO response) {

        Set<String> candidateSkills = analysis.getTechnicalSkills()
                .stream()
                .map(skill -> skill.trim().toLowerCase())
                .collect(Collectors.toSet());

        List<String> matchedSkills = new ArrayList<>();
        List<String> missingSkills = new ArrayList<>();

        for (String requiredSkill : job.getTechnicalSkills()) {

            String normalizedSkill =
                    requiredSkill.trim().toLowerCase();

            if (candidateSkills.contains(normalizedSkill)) {
                matchedSkills.add(requiredSkill);
            } else {
                missingSkills.add(requiredSkill);
            }

        }

        response.setMatchedTechnicalSkills(matchedSkills);
        response.setMissingTechnicalSkills(missingSkills);

        if (job.getTechnicalSkills().isEmpty()) {
            return 100;
        }

        return matchedSkills.size() * 100.0
                / job.getTechnicalSkills().size();
    }

    private double calculateSoftSkillScore(
            CandidateAnalysis analysis,
            Job job) {

        Set<String> candidateSoftSkills = analysis.getSoftSkills()
                .stream()
                .map(skill -> skill.trim().toLowerCase())
                .collect(Collectors.toSet());

        int matchedSkills = 0;

        for (String requiredSkill : job.getSoftSkills()) {

            if (candidateSoftSkills.contains(requiredSkill.trim().toLowerCase())) {
                matchedSkills++;
            }
        }

        if (job.getSoftSkills().isEmpty()) {
            return 100;
        }

        return matchedSkills * 100.0 / job.getSoftSkills().size();
    }

    private double calculateSeniorityScore(
            CandidateAnalysis analysis,
            Job job) {

        if (analysis.getSeniority() == null ||
                job.getRequiredSeniority() == null) {
            return 0;
        }

        if (analysis.getSeniority().equalsIgnoreCase(
                job.getRequiredSeniority().name())) {
            return 100;
        }

        return 0;
    }

    private double calculateOverallScore(
            double technicalScore,
            double softSkillScore,
            double seniorityScore) {

        return (technicalScore * 0.70)
                + (softSkillScore * 0.20)
                + (seniorityScore * 0.10);
    }

    public List<MatchRankingDTO> rankCandidates(Long jobId) {

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Job not found"));

        return candidateAnalysisRepository.findAll()
                .stream()
                .map(analysis -> {

                    double technicalScore =
                            calculateTechnicalScore(
                                    analysis,
                                    job,
                                    new MatchResponseDTO());

                    double softSkillScore =
                            calculateSoftSkillScore(
                                    analysis,
                                    job);

                    double seniorityScore =
                            calculateSeniorityScore(
                                    analysis,
                                    job);

                    double overallScore =
                            calculateOverallScore(
                                    technicalScore,
                                    softSkillScore,
                                    seniorityScore);

                    MatchRankingDTO response = new MatchRankingDTO();

                    response.setCandidateId(
                            analysis.getCandidate().getId());

                    response.setCandidateName(
                            analysis.getCandidate().getDisplayName());

                    response.setOverallScore(round(overallScore));

                    return response;

                })
                .sorted((a, b) ->
                        Double.compare(
                                b.getOverallScore(),
                                a.getOverallScore()))
                .toList();
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

}