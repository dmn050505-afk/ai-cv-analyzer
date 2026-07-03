package com.nunes.ai_cv_analyzer.controller;

import com.nunes.ai_cv_analyzer.dto.JobRequestDTO;
import com.nunes.ai_cv_analyzer.dto.JobResponseDTO;
import com.nunes.ai_cv_analyzer.service.JobService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PreAuthorize("hasAnyRole('ADMIN','RECRUITER')")
@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public JobResponseDTO createJob(@Valid @RequestBody JobRequestDTO requestDTO) {
        return jobService.createJob(requestDTO);
    }

    @GetMapping
    public List<JobResponseDTO> getAllJobs() {
        return jobService.getAllJobs();
    }

    @GetMapping("/{id}")
    public JobResponseDTO getJobById(@PathVariable Long id) {
        return jobService.getJobById(id);
    }

    @PutMapping("/{id}")
    public JobResponseDTO updateJob(
            @PathVariable Long id,
            @Valid @RequestBody JobRequestDTO requestDTO) {

        return jobService.updateJob(id, requestDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
    }
}