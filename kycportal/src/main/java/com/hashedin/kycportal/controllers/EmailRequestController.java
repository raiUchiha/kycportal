package com.hashedin.kycportal.controllers;

import com.hashedin.kycportal.repository.EmailRequest;
import com.hashedin.kycportal.security.services.EmailRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class EmailRequestController {
    @Autowired
    private EmailRequestService emailService;
    @RequestMapping(value = "/sendemail",method = RequestMethod.POST)
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest emailRequest){
        System.out.println(emailRequest);
        boolean tt=this.emailService.sendEmail(emailRequest.getSubject(),emailRequest.getMessage(),emailRequest.getTo());
        if(tt) {
            return ResponseEntity.ok("Message sent Successfully");
        }
        else{
            return ResponseEntity.ok("Message not sent");
        }
    }
}
