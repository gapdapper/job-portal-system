package com.jobportal.jobportal.repository;

import com.jobportal.jobportal.entitiy.JobPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface JobPostRepository extends JpaRepository<JobPost, Long> {

    Page<JobPost> findAll(Pageable pageable);
    Page<JobPost> findByTitleContainingIgnoreCase(String keyword, Pageable pageable);
    Page<JobPost> findByCompanyIndustryIgnoreCase(String keyword, Pageable pageable);
}
