package com.jobportal.jobportal.repository;

import com.jobportal.jobportal.entitiy.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findByUserId(Integer id);
}
