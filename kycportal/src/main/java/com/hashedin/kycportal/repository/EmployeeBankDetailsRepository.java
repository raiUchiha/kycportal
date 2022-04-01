package com.hashedin.kycportal.repository;

import com.hashedin.kycportal.models.EmployeeBankDetails;
import com.hashedin.kycportal.models.EmployeePersonalDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeBankDetailsRepository extends JpaRepository<EmployeeBankDetails,Long> {
    @Query(nativeQuery= true, value = "select * from  employee_bank_details where user_id= ?1")
    public EmployeeBankDetails getEmployeeDetails(Long userId);
}
