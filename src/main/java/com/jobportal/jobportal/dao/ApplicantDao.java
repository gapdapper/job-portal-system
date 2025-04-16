package com.jobportal.jobportal.dao;

import com.jobportal.jobportal.entitiy.Applicant;

import java.util.Optional;

public interface ApplicantDao {
    Optional<Applicant> findById(Long id);
}
