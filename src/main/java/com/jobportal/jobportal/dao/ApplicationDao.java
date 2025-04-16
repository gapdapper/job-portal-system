package com.jobportal.jobportal.dao;

import com.jobportal.jobportal.entitiy.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ApplicationDao {
    Application save(Application application);
    Page<Application> findByJobPostId(Long id, Pageable pageable);
}
