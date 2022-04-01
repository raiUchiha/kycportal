package com.hashedin.kycportal.repository;

import com.hashedin.kycportal.models.EmployeeEducation;
import com.hashedin.kycportal.models.EmployeePersonalDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeEducationRepository extends JpaRepository<com.hashedin.kycportal.models.EmployeeEducation,Long> {
    @Query(nativeQuery= true, value = "select * from  employee_education where user_id= ?1")
    public EmployeeEducation getEmployeeDetails(Long userId);
}
