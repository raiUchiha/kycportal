package com.hashedin.kycportal.controllers;

import com.hashedin.kycportal.models.EmployeeAddressAndContact;
import com.hashedin.kycportal.models.EmployeeAttachments;
import com.hashedin.kycportal.models.EmployeePersonalDetail;
import com.hashedin.kycportal.models.User;
import com.hashedin.kycportal.repository.EmployeeAttachmentsRepository;
import com.hashedin.kycportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class EmployeeAttachmentsController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmployeeAttachmentsRepository attachmentsRepository;

    @GetMapping("/{userId}/attachments")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public EmployeeAttachments getAttachments(@PathVariable("userId") long userId){
        return attachmentsRepository.getEmployeeDetails(userId);
    }

    @PutMapping("/{userId}/editAttachments")
    @PreAuthorize("hasRole('USER')")
    public EmployeeAttachments editDetails(@RequestBody EmployeeAttachments attachments , @PathVariable("userId") long userId){
        User user = userRepository.findById(userId).get();
        attachments.setUserId(user);

//        EmployeePersonalDetail employeePersonalDetail = detailsRepository.findById(id).get();
        EmployeeAttachments employeeAttachments = attachmentsRepository.getEmployeeDetails(userId);

        if(attachments.getAadharUrl()!=null) employeeAttachments.setAadharUrl(attachments.getAadharUrl());
        if(attachments.getPanUrl()!=null) employeeAttachments.setPanUrl(attachments.getPanUrl());
        if(attachments.getGraduationUrl()!=null) employeeAttachments.setGraduationUrl(attachments.getGraduationUrl());
        if(attachments.getGraduationUrl()!=null) employeeAttachments.setPostGraduationUrl(attachments.getPostGraduationUrl());
        if(attachments.getHighschoolUrl()!=null) employeeAttachments.setHighschoolUrl(attachments.getHighschoolUrl());
        if(attachments.getInterUrl()!=null) employeeAttachments.setInterUrl(attachments.getInterUrl());

        return attachmentsRepository.save(employeeAttachments);
    }

}
