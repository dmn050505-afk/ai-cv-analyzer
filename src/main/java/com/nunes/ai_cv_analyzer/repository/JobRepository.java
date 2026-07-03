package com.nunes.ai_cv_analyzer.repository;

import com.nunes.ai_cv_analyzer.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
}