package com.nunes.ai_cv_analyzer.mapper;

import com.nunes.ai_cv_analyzer.dto.JobRequestDTO;
import com.nunes.ai_cv_analyzer.dto.JobResponseDTO;
import com.nunes.ai_cv_analyzer.entity.Job;
import org.springframework.stereotype.Component;

@Component
public class JobMapper {

    public Job toEntity(JobRequestDTO requestDTO) {

        Job job = new Job();

        job.setTitle(requestDTO.getTitle());
        job.setDescription(requestDTO.getDescription());
        job.setRequiredSeniority(requestDTO.getRequiredSeniority());
        job.setTechnicalSkills(requestDTO.getTechnicalSkills());
        job.setSoftSkills(requestDTO.getSoftSkills());
        job.setStatus(requestDTO.getStatus());

        return job;
    }

    public JobResponseDTO toResponseDTO(Job job) {

        JobResponseDTO responseDTO = new JobResponseDTO();

        responseDTO.setId(job.getId());
        responseDTO.setTitle(job.getTitle());
        responseDTO.setDescription(job.getDescription());
        responseDTO.setRequiredSeniority(job.getRequiredSeniority());
        responseDTO.setTechnicalSkills(job.getTechnicalSkills());
        responseDTO.setSoftSkills(job.getSoftSkills());
        responseDTO.setStatus(job.getStatus());
        responseDTO.setCreatedAt(job.getCreatedAt());
        responseDTO.setUpdatedAt(job.getUpdatedAt());

        return responseDTO;
    }
}