package com.hashedin.kycportal.repository;

import com.hashedin.kycportal.models.EmployeeAddressAndContact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeAddressRepository extends JpaRepository<EmployeeAddressAndContact,Long> {
}
