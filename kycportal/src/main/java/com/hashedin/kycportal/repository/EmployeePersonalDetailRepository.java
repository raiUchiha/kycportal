package com.hashedin.kycportal.repository;

import com.hashedin.kycportal.models.EmployeePersonalDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeePersonalDetailRepository extends JpaRepository<EmployeePersonalDetail, Long> {
    }
