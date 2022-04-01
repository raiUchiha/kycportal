package com.hashedin.kycportal.repository;

import com.hashedin.kycportal.models.EmployeeAddressAndContact;
import com.hashedin.kycportal.models.EmployeeBankDetails;
import com.hashedin.kycportal.models.EmployeePersonalDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeAddressRepository extends JpaRepository<EmployeeAddressAndContact,Long> {
    @Query(nativeQuery= true, value = "select * from  employee_address_and_contact where user_id= ?1")
    public EmployeeAddressAndContact getEmployeeDetails(Long userId);
}
