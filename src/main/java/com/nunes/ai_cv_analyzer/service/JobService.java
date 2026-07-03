package com.nunes.ai_cv_analyzer.service;

import com.nunes.ai_cv_analyzer.dto.JobRequestDTO;
import com.nunes.ai_cv_analyzer.dto.JobResponseDTO;
import com.nunes.ai_cv_analyzer.entity.Job;
import com.nunes.ai_cv_analyzer.mapper.JobMapper;
import com.nunes.ai_cv_analyzer.repository.JobRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final JobMapper jobMapper;

    public JobService(JobRepository jobRepository, JobMapper jobMapper) {
        this.jobRepository = jobRepository;
        this.jobMapper = jobMapper;
    }

    public JobResponseDTO createJob(JobRequestDTO requestDTO) {

        Job job = jobMapper.toEntity(requestDTO);

        job.setCreatedAt(LocalDateTime.now());
        job.setUpdatedAt(LocalDateTime.now());

        Job savedJob = jobRepository.save(job);

        return jobMapper.toResponseDTO(savedJob);
    }

    public List<JobResponseDTO> getAllJobs() {

        return jobRepository.findAll()
                .stream()
                .map(jobMapper::toResponseDTO)
                .toList();
    }

    public JobResponseDTO getJobById(Long id) {

        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Job not found"));

        return jobMapper.toResponseDTO(job);
    }

    public JobResponseDTO updateJob(Long id, JobRequestDTO requestDTO) {

        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Job not found"));

        job.setTitle(requestDTO.getTitle());
        job.setDescription(requestDTO.getDescription());
        job.setRequiredSeniority(requestDTO.getRequiredSeniority());
        job.setTechnicalSkills(requestDTO.getTechnicalSkills());
        job.setSoftSkills(requestDTO.getSoftSkills());
        job.setStatus(requestDTO.getStatus());
        job.setUpdatedAt(LocalDateTime.now());

        Job updatedJob = jobRepository.save(job);

        return jobMapper.toResponseDTO(updatedJob);
    }

    public void deleteJob(Long id) {

        if (!jobRepository.existsById(id)) {
            throw new EntityNotFoundException("Job not found");
        }

        jobRepository.deleteById(id);
    }
}