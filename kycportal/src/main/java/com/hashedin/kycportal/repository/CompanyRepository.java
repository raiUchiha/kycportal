package com.hashedin.kycportal.repository;

import com.hashedin.kycportal.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Long> {
}
