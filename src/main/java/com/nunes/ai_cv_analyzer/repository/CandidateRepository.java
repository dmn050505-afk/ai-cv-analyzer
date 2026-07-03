package com.nunes.ai_cv_analyzer.repository;

import com.nunes.ai_cv_analyzer.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

}