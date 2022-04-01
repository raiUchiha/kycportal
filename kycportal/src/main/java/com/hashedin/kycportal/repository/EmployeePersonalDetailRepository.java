package com.hashedin.kycportal.repository;

import com.hashedin.kycportal.models.EmployeePersonalDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeePersonalDetailRepository extends JpaRepository<EmployeePersonalDetail, Long> {
    @Query(nativeQuery= true, value = "select * from  employee_personal_detail where user_id= ?1")
    public EmployeePersonalDetail getEmployeeDetails( Long userId);
}
