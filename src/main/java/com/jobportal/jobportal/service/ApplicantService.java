package com.jobportal.jobportal.service;

import com.jobportal.jobportal.entitiy.Applicant;

import java.util.Optional;

public interface ApplicantService {
    Optional<Applicant> findById(Long id);
    Applicant findByUserId(Integer id);
}
