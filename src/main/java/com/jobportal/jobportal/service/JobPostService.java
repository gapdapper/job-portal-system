package com.jobportal.jobportal.service;

import com.jobportal.jobportal.entitiy.JobPost;
import com.jobportal.jobportal.entitiy.JobPostDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface JobPostService {
    Page<JobPost> getJobPosts(String keyword ,Pageable pageable);
    Page<JobPost> getJobPostsByIndustry(String industry, Pageable pageable);
    JobPost findById(Long id);
    JobPost save(JobPost jobPost);
    JobPost updateJobPost(Long id,JobPostDto dto);
    void deleteById(Long id);
}
