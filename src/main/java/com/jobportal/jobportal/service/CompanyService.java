package com.jobportal.jobportal.service;

import com.jobportal.jobportal.entitiy.Company;

public interface CompanyService {
    Company getCompany(Long id);
    Company findCompanyByUserId(Integer id);
}
