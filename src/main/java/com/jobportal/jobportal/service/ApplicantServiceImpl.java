package com.jobportal.jobportal.service;

import com.jobportal.jobportal.dao.ApplicantDao;
import com.jobportal.jobportal.entitiy.Applicant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApplicantServiceImpl implements ApplicantService{

    final ApplicantDao applicantDao;


    @Override
    public Optional<Applicant> findById(Long id) {
        return applicantDao.findById(id);
    }

    @Override
    public Applicant findByUserId(Integer id) {
        return applicantDao.findByUserId(id);
    }
}
