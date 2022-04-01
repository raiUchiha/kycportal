package com.hashedin.kycportal.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.hashedin.kycportal.models.*;
import com.hashedin.kycportal.payload.request.LoginRequest;
import com.hashedin.kycportal.payload.request.SignupRequest;
import com.hashedin.kycportal.payload.response.JwtResponse;
import com.hashedin.kycportal.payload.response.MessageResponse;
import com.hashedin.kycportal.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hashedin.kycportal.security.jwt.JwtUtils;
import com.hashedin.kycportal.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @Autowired
  EmployeePersonalDetailRepository personalDetail;

  @Autowired
  EmployeeBankDetailsRepository bankDetailsRepository;

  @Autowired
  EmployeeEducationRepository educationRepository;

  @Autowired
  EmployeeAddressRepository addressRepository;

  @Autowired
  EmployeeAttachmentsRepository attachmentsRepository;

  @Autowired
  EmployementRepository employementRepository;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);
    
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();    
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

    return ResponseEntity.ok(new JwtResponse(jwt,
                         userDetails.getId(), 
                         userDetails.getUsername(), 
                         userDetails.getEmail(), 
                         roles));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new user's account
    User user = new User(signUpRequest.getUsername(),
               signUpRequest.getEmail(),
               encoder.encode(signUpRequest.getPassword()),
               signUpRequest.getCompany());

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
        case "admin":
          Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(adminRole);

          break;
        case "mod":
          Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(modRole);

          break;
        default:
          Role userRole = roleRepository.findByName(ERole.ROLE_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(userRole);
        }
      });
    }

    user.setRoles(roles);
    User savedUse = userRepository.save(user);

    EmployeePersonalDetail personalDetails = new EmployeePersonalDetail();
    personalDetails.setUserId(savedUse);
    personalDetail.save(personalDetails);

    EmployeeBankDetails bankDetails = new EmployeeBankDetails();
    bankDetails.setUserId(savedUse);
    bankDetailsRepository.save(bankDetails);

    EmployeeEducation employeeEducation = new EmployeeEducation();
    employeeEducation.setUserId(savedUse);
    educationRepository.save(employeeEducation);

    EmployeeAttachments attachments = new EmployeeAttachments();
    attachments.setUserId(savedUse);
    attachmentsRepository.save(attachments);

    EmployeeAddressAndContact addressAndContact = new EmployeeAddressAndContact();
    addressAndContact.setUserId(savedUse);
    addressRepository.save(addressAndContact);

    Employement employement = new Employement();
    employement.setUserId(savedUse);
    employementRepository.save(employement);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }
}
