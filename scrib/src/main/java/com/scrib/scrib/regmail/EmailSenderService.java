package com.scrib.scrib.regmail;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

import static com.scrib.scrib.constant.EmailConstant.*;

@Service
@AllArgsConstructor
public class EmailSenderService implements EmailSender{

    private final static Logger LOGGER= LoggerFactory
            .getLogger(EmailSenderService.class);

    @Autowired
    private JavaMailSender javaMailSender;



//        public void sendNewPasswordEmail (String firstName
//                , String password
//                , String email) throws MessagingException {
//            SimpleMailMessage message= new SimpleMailMessage();
//
//            message.setFrom("mmihai5522@gmail.com");
//            message.setTo(email);
//            message.setText("Hello "
//                + firstName
//                + ", \n \n Your account password is: "
//                + password
//                + "\n \n the Support team!");
//            message.setSubject(EMAIL_SUBJECT);
//            message.setSentDate(new Date());
//            javaMailSender.send(message);
//            System.out.println("Success!");
//
//
//    }


    @Override
    @Async
    public void send(String to, String email){
        try {

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(message, "utf-8");

            helper.setText(email,true);
            helper.setTo(to);
            helper.setSubject(EMAIL_SUBJECT);
            helper.setFrom(FROM_EMAIL);
            javaMailSender.send(message);
        } catch (MessagingException e){
            LOGGER.error("FAILED",e);

            throw new IllegalStateException("Failed email!");

        }


    }
}
