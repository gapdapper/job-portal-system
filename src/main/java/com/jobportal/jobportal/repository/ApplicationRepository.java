package com.jobportal.jobportal.repository;

import com.jobportal.jobportal.entitiy.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    Page<Application> findByJobPostId(Long id, Pageable pageable);
    List<Application> findByApplicantId(Long id);
}
