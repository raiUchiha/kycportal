package com.hashedin.kycportal.controllers;

import com.hashedin.kycportal.models.EmployeeAddressAndContact;
import com.hashedin.kycportal.models.EmployeePersonalDetail;
import com.hashedin.kycportal.models.User;
import com.hashedin.kycportal.repository.EmployeeAddressRepository;
import com.hashedin.kycportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class EmployeeAddressController {
    @Autowired
    private EmployeeAddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{userId}/addressDetails")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public EmployeeAddressAndContact getAddress(@PathVariable("userId") long userId){
        return addressRepository.getEmployeeDetails(userId);
    }

    @PostMapping("/address/{userId}/add")
    @PreAuthorize("hasRole('USER')")
    public EmployeeAddressAndContact addContact(@RequestBody EmployeeAddressAndContact addressDetails, @PathVariable("userId") long userId) {
        User user = userRepository.findById(userId).get();
        addressDetails.setUserId(user);
        return addressRepository.save(addressDetails);
    }

    @PutMapping("/{userId}/editAddress")
    @PreAuthorize("hasRole('USER')")
    public EmployeeAddressAndContact editDetails(@RequestBody EmployeeAddressAndContact address , @PathVariable("userId") long userId){
        User user = userRepository.findById(userId).get();
        address.setUserId(user);

//        EmployeePersonalDetail employeePersonalDetail = detailsRepository.findById(id).get();
        EmployeeAddressAndContact employeeAddressAndContact = addressRepository.getEmployeeDetails(userId);

        if(address.getHouseNo()!=null) employeeAddressAndContact.setHouseNo(address.getHouseNo());
        if(address.getArea()!=null) employeeAddressAndContact.setArea(address.getArea());
        if(address.getCity()!=null) employeeAddressAndContact.setCity(address.getCity());
        if(address.getState()!=null) employeeAddressAndContact.setState(address.getState());
        if(address.getPinCode()!=0) employeeAddressAndContact.setPinCode(address.getPinCode());
        if(address.getCountry()!=null) employeeAddressAndContact.setCountry(address.getCountry());
        if(address.getContactNo()!=0) employeeAddressAndContact.setContactNo(address.getContactNo());

        return addressRepository.save(employeeAddressAndContact);
    }
}
