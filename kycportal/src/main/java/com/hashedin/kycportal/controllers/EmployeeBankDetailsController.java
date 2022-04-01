package com.hashedin.kycportal.controllers;

import com.hashedin.kycportal.models.EmployeeBankDetails;
import com.hashedin.kycportal.models.User;
import com.hashedin.kycportal.repository.EmployeeBankDetailsRepository;
import com.hashedin.kycportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class EmployeeBankDetailsController {
    @Autowired
    private EmployeeBankDetailsRepository bankRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{userId}/bankDetails")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public EmployeeBankDetails getBankDetails(@PathVariable("userId") long userId){
        return bankRepository.getEmployeeDetails(userId);
    }

    @PostMapping("/bank/{userId}/add")
    @PreAuthorize("hasRole('USER')")
    public EmployeeBankDetails addCourse(@RequestBody EmployeeBankDetails bankDetails, @PathVariable("userId") long userId) {
        User user = userRepository.findById(userId).get();
        bankDetails.setUserId(user);
        return bankRepository.save(bankDetails);
    }

    @PutMapping("/bank/{userId}/update")
    @PreAuthorize("hasRole('USER')")
    public EmployeeBankDetails updateBank(@RequestBody EmployeeBankDetails bankDetails, @PathVariable("userId") long userId){
        User user = userRepository.findById(userId).get();
        bankDetails.setUserId(user);
        EmployeeBankDetails employeeBankDetails=bankRepository.getEmployeeDetails(userId);
        if(bankDetails.getBankName()!=null) employeeBankDetails.setBankName(bankDetails.getBankName());

        if(bankDetails.getAccountNo()!=0) employeeBankDetails.setAccountNo(bankDetails.getAccountNo());

        if(bankDetails.getIFCS()!=null) employeeBankDetails.setIFCS(bankDetails.getIFCS());

        return bankRepository.save(employeeBankDetails);
    }
}
