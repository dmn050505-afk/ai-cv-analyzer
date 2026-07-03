package com.nunes.ai_cv_analyzer.mapper;

import com.nunes.ai_cv_analyzer.dto.CandidateAnalysisResponseDTO;
import com.nunes.ai_cv_analyzer.entity.CandidateAnalysis;

public class CandidateAnalysisMapper {

    public static CandidateAnalysisResponseDTO toResponseDTO(
            CandidateAnalysis analysis) {

        CandidateAnalysisResponseDTO dto =
                new CandidateAnalysisResponseDTO();

        dto.setCandidateId(
                analysis.getCandidate().getId());

        dto.setTechnicalSkills(
                analysis.getTechnicalSkills());

        dto.setSoftSkills(
                analysis.getSoftSkills());

        dto.setSeniority(
                analysis.getSeniority());

        dto.setStrengths(
                analysis.getStrengths());

        dto.setWeaknesses(
                analysis.getWeaknesses());

        return dto;
    }

}