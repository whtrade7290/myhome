package com.godcoder.myhome.Service;

import com.godcoder.myhome.model.MailDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendSimpleMessage(MailDto mailDto) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("dnrkf7266@hanmail.net");
            message.setTo(mailDto.getAddress());
            message.setSubject(mailDto.getTitle());
            message.setText(mailDto.getContent());

            try {
                javaMailSender.send(message);
            } catch (MailException e){
                e.printStackTrace();
            }

    }
}
