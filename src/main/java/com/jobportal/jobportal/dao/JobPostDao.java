package com.jobportal.jobportal.dao;

import com.jobportal.jobportal.entitiy.JobPost;
import com.jobportal.jobportal.entitiy.JobPostDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface JobPostDao {
    Page<JobPost> getJobPosts(Pageable pageable);
    Page<JobPost> getJobPostsByKeyword(String keyword,Pageable pageable);
    Page<JobPost> getJobPostsByIndustry(String industry, Pageable pageable);
    JobPost findById(Long id);
    JobPost save(JobPost jobPost);
    void deleteById(Long id);
}
