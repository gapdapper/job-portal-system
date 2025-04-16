package com.jobportal.jobportal.dao;

import com.jobportal.jobportal.entitiy.JobPost;
import com.jobportal.jobportal.entitiy.JobPostDto;
import com.jobportal.jobportal.repository.JobPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JobPostDaoImpl implements JobPostDao{

    final JobPostRepository jobPostRepository;

    @Override
    public Page<JobPost> getJobPosts(Pageable pageable) {
        return jobPostRepository.findAll(pageable);
    }

    @Override
    public Page<JobPost> getJobPostsByKeyword(String keyword,Pageable pageable) {
        return jobPostRepository.findByTitleContainingIgnoreCase(keyword, pageable);
    }

    @Override
    public Page<JobPost> getJobPostsByIndustry(String industry, Pageable pageable) {
        return jobPostRepository.findByCompanyIndustryIgnoreCase(industry, pageable);
    }

    @Override
    public JobPost findById(Long id) {
        return jobPostRepository.findById(id).orElse(null);
    }

    @Override
    public JobPost save(JobPost jobPost) {
        return jobPostRepository.save(jobPost);
    }

    @Override
    public void deleteById(Long id) {
        jobPostRepository.deleteById(id);
    }

}
