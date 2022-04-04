package com.hashedin.kycportal.controllers;

import com.hashedin.kycportal.models.User;
import com.hashedin.kycportal.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

  @Autowired
  UserRepository userRepository;

  @Autowired
  private EmployeePersonalDetailRepository detailsRepository;

  @Autowired
  private EmployementRepository employementRepository;

  @Autowired
  private EmployeeEducationRepository educationRepository;

  @Autowired
  private EmployeeAttachmentsRepository attachmentsRepository;

  @Autowired
  private EmployeeBankDetailsRepository bankRepository;

  @Autowired
  private EmployeeAddressRepository addressRepository;

  @GetMapping("/all")
  public String allAccess() {
    return "Public Content.";
  }

  @GetMapping("/user")
  @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
  public List<User> allUser() {
    return userRepository.findAll();
  }

  @GetMapping("/mod")
  @PreAuthorize("hasRole('MODERATOR')")
  public String moderatorAccess() {
    return "Moderator Board.";
  }

  @GetMapping("/admin")
  @PreAuthorize("hasRole('ADMIN')")
  public String adminAccess() {
    return "Admin Board.";
  }



}
