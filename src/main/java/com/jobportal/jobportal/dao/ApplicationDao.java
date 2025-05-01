package com.jobportal.jobportal.dao;

import com.jobportal.jobportal.entitiy.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ApplicationDao {
    Application save(Application application);
    Page<Application> findByJobPostId(Long id, Pageable pageable);
    List<Application> findByApplicantId(Long id);
}
