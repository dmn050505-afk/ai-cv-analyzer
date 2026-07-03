package com.nunes.ai_cv_analyzer.controller;


import com.nunes.ai_cv_analyzer.service.CandidateService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.nunes.ai_cv_analyzer.dto.CandidateRequestDTO;
import com.nunes.ai_cv_analyzer.dto.CandidateResponseDTO;
import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;

import java.util.List;


@PreAuthorize("hasAnyRole('ADMIN','RECRUITER')")
@RestController
@RequestMapping("/candidates")
public class CandidateController {

    private final CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @PostMapping
    public CandidateResponseDTO createCandidate(
            @Valid @RequestBody CandidateRequestDTO requestDTO) {

        return candidateService.createCandidate(requestDTO);
    }
    @GetMapping
    public List<CandidateResponseDTO> getAllCandidates() {
        return candidateService.getAllCandidates();
    }

    @GetMapping("/{id}")
    public CandidateResponseDTO getCandidateById(
            @PathVariable Long id) {

        return candidateService.getCandidateById(id);
    }

    @PutMapping("/{id}")
    public CandidateResponseDTO updateCandidate(
            @PathVariable Long id,
            @Valid @RequestBody CandidateRequestDTO requestDTO) {

        return candidateService.updateCandidate(id, requestDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCandidate(@PathVariable Long id) {
        candidateService.deleteCandidate(id);
    }

    @PostMapping(
            value = "/{id}/upload-cv",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public CandidateResponseDTO uploadCv(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file
    ) {

        return candidateService.uploadCv(
                id,
                file
        );
    }
}