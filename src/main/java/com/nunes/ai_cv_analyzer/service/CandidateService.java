package com.nunes.ai_cv_analyzer.service;

import com.nunes.ai_cv_analyzer.dto.CandidateRequestDTO;
import com.nunes.ai_cv_analyzer.dto.CandidateResponseDTO;
import com.nunes.ai_cv_analyzer.entity.Candidate;
import com.nunes.ai_cv_analyzer.exception.CandidateNotFoundException;
import com.nunes.ai_cv_analyzer.integration.gemini.dto.CVAnalysisDTO;
import com.nunes.ai_cv_analyzer.integration.gemini.service.GeminiService;
import com.nunes.ai_cv_analyzer.mapper.CandidateMapper;
import com.nunes.ai_cv_analyzer.repository.CandidateRepository;
import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CandidateService {

    private final CandidateRepository candidateRepository;
    private final FileStorageService fileStorageService;
    private final PdfExtractionService pdfExtractionService;
    private final GeminiService geminiService;
    private final CandidateAnalysisService candidateAnalysisService;

    public CandidateService(
            CandidateRepository candidateRepository,
            FileStorageService fileStorageService,
            PdfExtractionService pdfExtractionService,
            GeminiService geminiService,
            CandidateAnalysisService candidateAnalysisService) {

        this.candidateRepository = candidateRepository;
        this.fileStorageService = fileStorageService;
        this.pdfExtractionService = pdfExtractionService;
        this.geminiService = geminiService;
        this.candidateAnalysisService = candidateAnalysisService;
    }

    public CandidateResponseDTO createCandidate(
            CandidateRequestDTO requestDTO) {

        Candidate candidate =
                CandidateMapper.toEntity(requestDTO);

        candidate.setCreatedAt(LocalDateTime.now());

        Candidate savedCandidate =
                candidateRepository.save(candidate);

        return CandidateMapper.toResponseDTO(savedCandidate);
    }

    public List<CandidateResponseDTO> getAllCandidates() {

        List<Candidate> candidates =
                candidateRepository.findAll();

        return CandidateMapper.toResponseDTOList(candidates);
    }

    public CandidateResponseDTO getCandidateById(Long id) {

        Candidate candidate =
                candidateRepository.findById(id)
                        .orElseThrow(
                                () -> new CandidateNotFoundException(id)
                        );

        return CandidateMapper.toResponseDTO(candidate);
    }

    public CandidateResponseDTO updateCandidate(
            Long id,
            CandidateRequestDTO requestDTO) {

        Candidate candidate =
                candidateRepository.findById(id)
                        .orElseThrow(
                                () -> new CandidateNotFoundException(id)
                        );

        candidate.setFullName(requestDTO.getFullName());
        candidate.setEmail(requestDTO.getEmail());
        candidate.setPhone(requestDTO.getPhone());

        Candidate updatedCandidate =
                candidateRepository.save(candidate);

        return CandidateMapper.toResponseDTO(updatedCandidate);
    }

    public void deleteCandidate(Long id) {
        Candidate candidate =
                candidateRepository.findById(id)
                        .orElseThrow(
                                () -> new CandidateNotFoundException(id)
                        );

        candidateRepository.delete(candidate);
    }

    public CandidateResponseDTO uploadCv(
            Long candidateId,
            MultipartFile file
    ) {

        Candidate candidate =
                candidateRepository.findById(candidateId)
                        .orElseThrow(
                                () -> new CandidateNotFoundException(
                                        candidateId
                                )
                        );

        String filePath =
                fileStorageService.storeFile(
                        candidateId,
                        file
                );

        candidate.setCvFilePath(filePath);

        String extractedText =
                pdfExtractionService.extractText(
                        Path.of(filePath)
                );

        candidate.setExtractedText(extractedText);

        CVAnalysisDTO analysis = geminiService.analyze(extractedText);



        candidateRepository.save(candidate);

        candidateAnalysisService.save(candidate, analysis);

        return CandidateMapper.toResponseDTO(candidate);
    }

}