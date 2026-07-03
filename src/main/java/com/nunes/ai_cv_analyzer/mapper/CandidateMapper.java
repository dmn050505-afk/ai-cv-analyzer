package com.nunes.ai_cv_analyzer.mapper;

import com.nunes.ai_cv_analyzer.dto.CandidateRequestDTO;
import com.nunes.ai_cv_analyzer.dto.CandidateResponseDTO;
import com.nunes.ai_cv_analyzer.entity.Candidate;
import java.util.List;

public class CandidateMapper {

    public static Candidate toEntity(CandidateRequestDTO dto) {

        Candidate candidate = new Candidate();

        candidate.setFullName(dto.getFullName());
        candidate.setEmail(dto.getEmail());
        candidate.setPhone(dto.getPhone());

        return candidate;
    }

    public static CandidateResponseDTO toResponseDTO(Candidate candidate) {

        CandidateResponseDTO responseDTO =
                new CandidateResponseDTO();

        responseDTO.setId(candidate.getId());
        responseDTO.setFullName(candidate.getFullName());
        responseDTO.setEmail(candidate.getEmail());
        responseDTO.setPhone(candidate.getPhone());
        responseDTO.setCreatedAt(candidate.getCreatedAt());

        return responseDTO;
    }

    public static List<CandidateResponseDTO> toResponseDTOList(
            List<Candidate> candidates) {

        return candidates.stream()
                .map(CandidateMapper::toResponseDTO)
                .toList();
    }
}