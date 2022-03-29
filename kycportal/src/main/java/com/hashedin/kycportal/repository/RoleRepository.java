package com.hashedin.kycportal.repository;

import java.util.Optional;

import com.hashedin.kycportal.models.ERole;
import com.hashedin.kycportal.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
