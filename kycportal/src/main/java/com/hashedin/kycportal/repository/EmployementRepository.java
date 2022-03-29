package com.hashedin.kycportal.repository;

import com.hashedin.kycportal.models.Employement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployementRepository extends JpaRepository<Employement,Long> {
}
