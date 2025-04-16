package com.jobportal.jobportal.service;

import com.jobportal.jobportal.dao.CompanyDao;
import com.jobportal.jobportal.dao.JobPostDao;
import com.jobportal.jobportal.entitiy.Company;
import com.jobportal.jobportal.entitiy.JobPost;
import com.jobportal.jobportal.entitiy.JobPostDto;
import com.jobportal.jobportal.util.JobPostMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobPostServiceImpl implements JobPostService{

    final JobPostDao jobPostDao;
    final CompanyDao companyDao;
    final JobPostMapper jobPostMapper;



    @Override
    public Page<JobPost> getJobPosts(String keyword, Pageable pageable) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return jobPostDao.getJobPosts(pageable);
        } else {
            return jobPostDao.getJobPostsByKeyword(keyword, pageable);
        }
    }

    @Override
    public Page<JobPost> getJobPostsByIndustry(String industry, Pageable pageable) {
        return jobPostDao.getJobPostsByIndustry(industry, pageable);
    }

    @Override
    public JobPost findById(Long id) {
        return jobPostDao.findById(id);
    }

    @Override
    @Transactional
    public JobPost save(JobPost jobPost) {
        if (jobPost.getCompany() == null || jobPost.getCompany().getId() == null) {
            throw new IllegalArgumentException("Company ID is required");
        }

        Company company = companyDao.getCompany(jobPost.getCompany().getId());

        if (company == null) {
            throw new IllegalArgumentException("Company not found for ID: " + jobPost.getCompany().getId());
        }
        jobPost.setCompany(company);
        return jobPostDao.save(jobPost);
    }

    @Override
    @Transactional
    public JobPost updateJobPost(Long id,JobPostDto dto) {
        JobPost job = jobPostDao.findById(id);
        if (job == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Job post not found with id: " + id);
        }
        jobPostMapper.updateJobPostFromDto(dto, job);
        return jobPostDao.save(job);
    }

    @Override
    public void deleteById(Long id) {
        if (jobPostDao.findById(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Job post not found with id: " + id);
        }
        jobPostDao.deleteById(id);
    }


}
