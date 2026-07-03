package com.nunes.ai_cv_analyzer.controller;

import com.nunes.ai_cv_analyzer.dto.CandidateAnalysisResponseDTO;
import com.nunes.ai_cv_analyzer.service.CandidateAnalysisService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@PreAuthorize("hasAnyRole('ADMIN','RECRUITER')")
@RestController
@RequestMapping("/candidate-analysis")
public class CandidateAnalysisController {

    private final CandidateAnalysisService service;

    public CandidateAnalysisController(
            CandidateAnalysisService service) {

        this.service = service;
    }

    @GetMapping("/{candidateId}")
    public CandidateAnalysisResponseDTO getAnalysis(
            @PathVariable Long candidateId) {

        return service.getByCandidateId(candidateId);
    }

}