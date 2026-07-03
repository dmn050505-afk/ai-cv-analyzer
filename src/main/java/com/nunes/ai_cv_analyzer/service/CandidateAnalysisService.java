package com.nunes.ai_cv_analyzer.service;

import com.nunes.ai_cv_analyzer.dto.CandidateAnalysisResponseDTO;
import com.nunes.ai_cv_analyzer.entity.Candidate;
import com.nunes.ai_cv_analyzer.entity.CandidateAnalysis;
import com.nunes.ai_cv_analyzer.integration.gemini.dto.CVAnalysisDTO;
import com.nunes.ai_cv_analyzer.mapper.CandidateAnalysisMapper;
import com.nunes.ai_cv_analyzer.repository.CandidateAnalysisRepository;
import org.springframework.stereotype.Service;

@Service
public class CandidateAnalysisService {

    private final CandidateAnalysisRepository repository;

    public CandidateAnalysisService(CandidateAnalysisRepository repository) {
        this.repository = repository;
    }

    public CandidateAnalysis save(Candidate candidate,
                                  CVAnalysisDTO dto) {

        CandidateAnalysis analysis = repository
                .findByCandidateId(candidate.getId())
                .orElse(new CandidateAnalysis());

        analysis.setCandidate(candidate);

        analysis.setTechnicalSkills(dto.getTechnicalSkills());

        analysis.setSoftSkills(dto.getSoftSkills());

        analysis.setSeniority(
                dto.getSeniority());

        analysis.setStrengths(dto.getStrengths());

        analysis.setWeaknesses(dto.getWeaknesses());

        return repository.save(analysis);
    }

    public CandidateAnalysisResponseDTO getByCandidateId(
            Long candidateId) {

        CandidateAnalysis analysis =
                repository.findByCandidateId(candidateId)
                        .orElseThrow(() ->
                                new RuntimeException("Analysis not found."));

        return CandidateAnalysisMapper.toResponseDTO(analysis);
    }
}