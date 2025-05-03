package com.jobportal.jobportal.service;

import com.jobportal.jobportal.entitiy.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ApplicationService {
    Application submitApplication(Long jobId, String applicantUsername);
    Page<Application> findByJobPostId(Long id, Pageable pageable);
    List<Application> findByApplicantId(Long id);
    Optional<Application> findById(Long id);

    Application shortlistApplication(Long jobId, Long appId, String username);
    Application rejectApplication(Long jobId, Long appId, String username);
}
