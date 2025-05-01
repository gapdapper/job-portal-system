package com.jobportal.jobportal.service;

import com.jobportal.jobportal.dao.CompanyDao;
import com.jobportal.jobportal.entitiy.Company;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService{

    final CompanyDao companyDao;


    @Override
    public Company getCompany(Long id) {
        return companyDao.getCompany(id);
    }

    @Override
    public Company findCompanyByUserId(Integer id) {
        return companyDao.findCompanyByUserId(id);
    }
}
