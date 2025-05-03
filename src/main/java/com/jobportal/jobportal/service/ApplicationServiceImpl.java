package com.jobportal.jobportal.service;

import com.jobportal.jobportal.dao.ApplicantDao;
import com.jobportal.jobportal.dao.ApplicationDao;
import com.jobportal.jobportal.dao.CompanyDao;
import com.jobportal.jobportal.dao.JobPostDao;
import com.jobportal.jobportal.entitiy.Applicant;
import com.jobportal.jobportal.entitiy.Application;
import com.jobportal.jobportal.entitiy.Company;
import com.jobportal.jobportal.entitiy.JobPost;
import com.jobportal.jobportal.repository.ApplicationRepository;
import com.jobportal.jobportal.user.User;
import com.jobportal.jobportal.user.UserDao;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService{

    final ApplicationDao applicationDao;
    final JobPostDao jobPostDao;
    final ApplicantDao applicantDao;
    final UserDao userDao;
    final ApplicationRepository applicationRepository;
    final CompanyDao companyDao;


    @Override
    public Application submitApplication(Long jobId, String applicantUsername) {

        User user = userDao.findByUsername(applicantUsername);
        Applicant applicant = applicantDao.findByUserId(user.getId());
        JobPost jobPost = jobPostDao.findById(jobId);

        if (jobPost == null){
            throw new EntityNotFoundException("Job post not found: ID " + jobId);
        }


        Application application = new Application();

        application.setApplicant(applicant);
        application.setAppliedDate(new Date());
        application.setJobPost(jobPost);
        application.setStatus("APPLIED");

        return applicationRepository.save(application);
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
    public Application shortlistApplication(Long jobId, Long appId, String username) {
        User currentUser = userDao.findByUsername(username);
        Company company = companyDao.findCompanyByUserId(currentUser.getId());
        JobPost jobPost = jobPostDao.findById(jobId);
        Optional<Application> applicationOptional = applicationDao.findById(appId);
        if (applicationOptional.isEmpty()) {
            throw new EntityNotFoundException("Application not found with ID: " + appId);
        }

        Application application = applicationOptional.get();

        if (!company.getId().equals(jobPost.getCompany().getId())) {
            throw new AccessDeniedException("You are not authorized to shortlist applications for this job.");
        }

        if (!jobPost.getId().equals(application.getJobPost().getId())) {
            throw new IllegalArgumentException("Application does not belong to the specified job post.");
        }

        application.setStatus("SHORTLISTED");
        return applicationDao.save(application);

    }

    @Override
    public Application rejectApplication(Long jobId, Long appId, String username) {
        User currentUser = userDao.findByUsername(username);
        Company company = companyDao.findCompanyByUserId(currentUser.getId());
        JobPost jobPost = jobPostDao.findById(jobId);
        Optional<Application> applicationOptional = applicationDao.findById(appId);
        if (applicationOptional.isEmpty()) {
            throw new EntityNotFoundException("Application not found with ID: " + appId);
        }

        Application application = applicationOptional.get();

        if (!company.getId().equals(jobPost.getCompany().getId())) {
            throw new AccessDeniedException("You are not authorized to reject applications for this job.");
        }

        if (!jobPost.getId().equals(application.getJobPost().getId())) {
            throw new IllegalArgumentException("Application does not belong to the specified job post.");
        }

        application.setStatus("REJECTED");
        return applicationDao.save(application);
    }
}
