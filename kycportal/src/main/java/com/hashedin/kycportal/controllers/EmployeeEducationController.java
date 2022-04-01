package com.hashedin.kycportal.controllers;

import com.hashedin.kycportal.models.EmployeeAddressAndContact;
import com.hashedin.kycportal.models.EmployeeEducation;
import com.hashedin.kycportal.models.User;
import com.hashedin.kycportal.repository.EmployeeEducationRepository;
import com.hashedin.kycportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class EmployeeEducationController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmployeeEducationRepository educationRepository;

    @GetMapping("/{userId}/education")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public EmployeeEducation getEducation(@PathVariable("userId") long userId){
        return educationRepository.getEmployeeDetails(userId);
    }

    @PutMapping("/{userId}/editEducation")
    @PreAuthorize("hasRole('USER')")
    public EmployeeEducation editEducation(@RequestBody EmployeeEducation education , @PathVariable("userId") long userId){
        User user = userRepository.findById(userId).get();
        education.setUserId(user);

        EmployeeEducation employeeEducation = educationRepository.getEmployeeDetails(userId);

        if(education.getHighestEducationDegree()!=null) employeeEducation.setHighestEducationDegree(education.getHighestEducationDegree());
        if(education.getCollege()!=null) employeeEducation.setCollege(education.getCollege());
        if(education.getUniversity()!=null) employeeEducation.setUniversity(education.getUniversity());
        if(education.getCpi()!=0) employeeEducation.setCpi(education.getCpi());
        if(education.getYearOfPassing()!=null) employeeEducation.setYearOfPassing(education.getYearOfPassing());

        return educationRepository.save(employeeEducation);
    }
}
