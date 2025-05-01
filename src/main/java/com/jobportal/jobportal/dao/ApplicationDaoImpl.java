package com.jobportal.jobportal.dao;

import com.jobportal.jobportal.entitiy.Application;
import com.jobportal.jobportal.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class ApplicationDaoImpl implements ApplicationDao{
    final ApplicationRepository applicationRepository;


    @Override
    public Application save(Application application) {
        return applicationRepository.save(application);
    }

    @Override
    public Page<Application> findByJobPostId(Long id, Pageable pageable) {
        return applicationRepository.findByJobPostId(id, pageable);
    }

    @Override
    public List<Application> findByApplicantId(Long id) {
        return applicationRepository.findByApplicantId(id);
    }

    @Override
    public Optional<Application> findById(Long id) {
        return applicationRepository.findById(id);
    }
}
