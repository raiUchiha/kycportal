package com.hashedin.kycportal.repository;

import com.hashedin.kycportal.models.EmployeeAttachments;
import com.hashedin.kycportal.models.EmployeePersonalDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeAttachmentsRepository extends JpaRepository<EmployeeAttachments,Long> {
    @Query(nativeQuery= true, value = "select * from  employee_attachments where user_id= ?1")
    public EmployeeAttachments getEmployeeDetails(Long userId);
}
