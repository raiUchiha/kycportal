package com.hashedin.kycportal.controllers;

import com.hashedin.kycportal.models.EmployeeEducation;
import com.hashedin.kycportal.models.Employement;
import com.hashedin.kycportal.models.User;
import com.hashedin.kycportal.repository.EmployementRepository;
import com.hashedin.kycportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class EmployementController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmployementRepository employementRepository;

    @GetMapping("/{userId}/employement")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Employement getEmployement(@PathVariable("userId") long userId){
        return employementRepository.getEmployeeDetails(userId);
    }

    @PutMapping("/{userId}/editEmployement")
    @PreAuthorize("hasRole('USER')")
    public Employement editEmployement(@RequestBody Employement employement , @PathVariable("userId") long userId){
        User user = userRepository.findById(userId).get();
        employement.setUserId(user);

        Employement employee = employementRepository.getEmployeeDetails(userId);

        if(employement.getPreviousCompany()!=null) employee.setPreviousCompany(employement.getPreviousCompany());
        if(employement.getTotalYearsOfExperience()!=0) employee.setTotalYearsOfExperience(employement.getTotalYearsOfExperience());
        if(employement.getJoiningDate()!=null) employee.setJoiningDate(employement.getJoiningDate());
        if(employement.getExitDate()!=null) employee.setExitDate(employement.getExitDate());
        if(employement.getUan()!=0) employee.setUan(employement.getUan());

        return employementRepository.save(employee);
    }
}
