package com.hashedin.kycportal.security.services;

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailRequestService {
    public boolean sendEmail(String subject,String message,String to){
        boolean f=false;
        String from="uzumki002@gmail.com";
        String host="smtp.gmail.com";

        //get the system properties
        Properties properties=System.getProperties();

        properties.put("mail.smtp.host",host);
        properties.put("mail.smtp.port",465);
        properties.put("mail.smtp.ssl.enable","true");
        properties.put("mail.smtp.auth","true");

        //step 1: to get the session object
        Session session=Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("uzumki002@gmail.com","jaypee01");
            }
        });
        session.setDebug(true);


        //step 2 :compose the message
        MimeMessage m=new MimeMessage(session);

        try {
            // from email
            m.setFrom(from);
            m.addRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress(to)));
            //adding subject to mesaage
            m.setSubject(subject);

            //adding tect to meaage
            m.setText(message);
            //send

            //step 3:send the message using transport class
            Transport.send(m);
            System.out.println("SEnt the mesage successfully");
            f=true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return f;
    }
}
