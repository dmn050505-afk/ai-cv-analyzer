package com.nunes.ai_cv_analyzer.controller;

import com.nunes.ai_cv_analyzer.dto.MatchRankingDTO;
import com.nunes.ai_cv_analyzer.dto.MatchResponseDTO;
import com.nunes.ai_cv_analyzer.service.MatchingService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@PreAuthorize("hasAnyRole('ADMIN','RECRUITER')")
@RestController
@RequestMapping("/matching")
public class MatchingController {

    private final MatchingService matchingService;

    public MatchingController(MatchingService matchingService) {
        this.matchingService = matchingService;
    }

    @GetMapping("/{candidateId}/{jobId}")
    public MatchResponseDTO match(
            @PathVariable Long candidateId,
            @PathVariable Long jobId) {

        return matchingService.match(candidateId, jobId);
    }

    @GetMapping("/job/{jobId}")
    public List<MatchRankingDTO> rankCandidates(
            @PathVariable Long jobId) {

        return matchingService.rankCandidates(jobId);
    }

}