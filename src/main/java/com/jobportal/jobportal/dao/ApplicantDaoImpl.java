package com.jobportal.jobportal.dao;

import com.jobportal.jobportal.entitiy.Applicant;
import com.jobportal.jobportal.repository.ApplicantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ApplicantDaoImpl implements ApplicantDao{
    final ApplicantRepository applicantRepository;

    @Override
    public Optional<Applicant> findById(Long id) {
        return applicantRepository.findById(id);
    }
}
