package com.jobportal.jobportal.service;

import com.jobportal.jobportal.entitiy.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ApplicationService {
    Application save(Application application);
    Page<Application> findByJobPostId(Long id, Pageable pageable);
}
