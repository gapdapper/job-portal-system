package com.jobportal.jobportal.dao;

import com.jobportal.jobportal.entitiy.Company;
import com.jobportal.jobportal.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class CompanyDaoImpl implements CompanyDao{

    final CompanyRepository companyRepository;

    @Override
    public Company getCompany(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public Company findCompanyByUserId(Integer id) {
        return companyRepository.findByUserId(id);
    }
}
