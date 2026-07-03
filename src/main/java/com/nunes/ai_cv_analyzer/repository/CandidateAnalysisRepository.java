package com.nunes.ai_cv_analyzer.repository;

import com.nunes.ai_cv_analyzer.entity.CandidateAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CandidateAnalysisRepository
        extends JpaRepository<CandidateAnalysis, Long> {

    Optional<CandidateAnalysis> findByCandidateId(Long candidateId);

}