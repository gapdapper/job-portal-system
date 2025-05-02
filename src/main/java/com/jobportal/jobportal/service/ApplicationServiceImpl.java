package com.jobportal.jobportal.service;

import com.jobportal.jobportal.dao.ApplicantDao;
import com.jobportal.jobportal.dao.ApplicationDao;
import com.jobportal.jobportal.dao.JobPostDao;
import com.jobportal.jobportal.entitiy.Applicant;
import com.jobportal.jobportal.entitiy.Application;
import com.jobportal.jobportal.entitiy.JobPost;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService{

    final ApplicationDao applicationDao;
    final JobPostDao jobPostDao;
    final ApplicantDao applicantDao;

    @Override
    public Application save(Application application) {
        Optional<Applicant> applicant = applicantDao.findById(application.getApplicant().getId());
        JobPost jobPost = jobPostDao.findById(application.getJobPost().getId());

        application.setApplicant(applicant.get());
        application.setJobPost(jobPost);

        return applicationDao.save(application);
    }

    @Override
    public Page<Application> findByJobPostId(Long id, Pageable pageable) {
        return applicationDao.findByJobPostId(id, pageable);
    }

    @Override
    public List<Application> findByApplicantId(Long id) {
        return applicationDao.findByApplicantId(id);
    }

    @Override
    public Optional<Application> findById(Long id) {
        return applicationDao.findById(id);
    }

    @Override
    public Application shortlistApplication(Long appId) {
        Optional<Application> applicationOptional = applicationDao.findById(appId);
        Application application = applicationOptional.get();

        application.setStatus("SHORTLISTED");
        return applicationDao.save(application);
    }


    @Override
    public Application rejectApplication(Long appId) {
        Optional<Application> applicationOptional = applicationDao.findById(appId);
        Application application = applicationOptional.get();

        application.setStatus("REJECTED");
        return applicationDao.save(application);
    }
}
