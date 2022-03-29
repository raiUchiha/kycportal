package com.hashedin.kycportal.controllers;

import com.hashedin.kycportal.models.EmployeeAddressAndContact;
import com.hashedin.kycportal.models.User;
import com.hashedin.kycportal.repository.EmployeeAddressRepository;
import com.hashedin.kycportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class EmployeeAddressController {
    @Autowired
    private EmployeeAddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{userId}/addressDetails")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public EmployeeAddressAndContact getAddress(@PathVariable("userId") long userId){
        return addressRepository.findById(userId).get();
    }

    @PostMapping("/address/{userId}/add")
    @PreAuthorize("hasRole('USER')")
    public EmployeeAddressAndContact addContact(@RequestBody EmployeeAddressAndContact addressDetails, @PathVariable("userId") long userId) {
        User user = userRepository.findById(userId).get();
        addressDetails.setUserId(user);
        return addressRepository.save(addressDetails);
    }
}
