package com.hashedin.kycportal.repository;

import com.hashedin.kycportal.models.EmployeePersonalDetail;
import com.hashedin.kycportal.models.Employement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployementRepository extends JpaRepository<Employement,Long> {
    @Query(nativeQuery= true, value = "select * from  employement where user_id= ?1")
    public Employement getEmployeeDetails(Long userId);
}
