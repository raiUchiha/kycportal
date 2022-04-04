package com.hashedin.kycportal.controllers;

import com.hashedin.kycportal.models.EmployeePersonalDetail;
import com.hashedin.kycportal.models.User;
import com.hashedin.kycportal.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class EmployeePersonalDetailController {
    @Autowired
    private EmployeePersonalDetailRepository detailsRepository;
    @Autowired
    private UserRepository userRepository;
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

    @GetMapping("/personalDetails")
    @PreAuthorize("hasRole('USER')")
    public List<EmployeePersonalDetail> getAllUser(){
        return detailsRepository.findAll();
    }

    @GetMapping("/{userId}/personalDetails")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public EmployeePersonalDetail getPersonalDetails(@PathVariable("userId") long userId){
        return detailsRepository.getEmployeeDetails(userId);
    }

    @PostMapping("/{userId}/addDetails")
    @PreAuthorize("hasRole('USER')")
    public EmployeePersonalDetail createUser(@RequestBody EmployeePersonalDetail personalDetail,@PathVariable("userId") long userId){
        User user = userRepository.findById(userId).get();
        personalDetail.setUserId(user);
        return detailsRepository.save(personalDetail);
    }



    @PutMapping("/{userId}/editDetails")
    @PreAuthorize("hasRole('USER')")
    public EmployeePersonalDetail editDetails(@RequestBody EmployeePersonalDetail personalDetail , @PathVariable("userId") long userId){
        User user = userRepository.findById(userId).get();
        personalDetail.setUserId(user);

//        EmployeePersonalDetail employeePersonalDetail = detailsRepository.findById(id).get();
        EmployeePersonalDetail employeePersonalDetail = detailsRepository.getEmployeeDetails(userId);

        if(personalDetail.getFirstName()!=null) employeePersonalDetail.setFirstName(personalDetail.getFirstName());
//        else employeePersonalDetail.setFirstName(personalDetail.getFirstName());

        if(personalDetail.getLastName()!=null) employeePersonalDetail.setLastName(personalDetail.getLastName());
//        else employeePersonalDetail.setLastName(personalDetail.getLastName());

        if(personalDetail.getCitizenship()!=null) employeePersonalDetail.setCitizenship(personalDetail.getCitizenship());
//        else employeePersonalDetail.setCitizenship(personalDetail.getCitizenship());

        if(personalDetail.getGender()!=null) employeePersonalDetail.setGender(personalDetail.getGender());
//        else employeePersonalDetail.setGender(personalDetail.getGender());

        if(personalDetail.getBloodGroup()!=null) employeePersonalDetail.setBloodGroup(personalDetail.getBloodGroup());
//        else employeePersonalDetail.setBloodGroup(personalDetail.getBloodGroup());

        if(personalDetail.getDob()!=null) employeePersonalDetail.setDob(personalDetail.getDob());
//        else employeePersonalDetail.setDob(personalDetail.getDob());

        return detailsRepository.save(employeePersonalDetail);
    }



    @GetMapping("/{userId}/allDetails")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public List<Object> getAllUserDetails(@PathVariable("userId") long userId) {
        User user = userRepository.findById(userId).get();
//    detailsRepository.getEmployeeDetails(userId)
        List <Object> details = new ArrayList<>();
        details.add(detailsRepository.getEmployeeDetails(userId));
        details.add(employementRepository.getEmployeeDetails(userId));
        details.add(educationRepository.getEmployeeDetails(userId));
        details.add(bankRepository.getEmployeeDetails(userId));
        details.add(attachmentsRepository.getEmployeeDetails(userId));
        details.add(addressRepository.getEmployeeDetails(userId));

        return details;
    }


}
