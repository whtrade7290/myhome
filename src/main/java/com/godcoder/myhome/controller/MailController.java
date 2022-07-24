package com.godcoder.myhome.controller;

import com.godcoder.myhome.Service.EmailService;
import com.godcoder.myhome.model.MailDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mail")
@Slf4j
public class MailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/send")
    public String sendMail() {
        log.info("SendMail() 호출됨");
        return"mail/sendMail";
    }

    @PostMapping("/send")
    public String sendMail(MailDto mailDto) {
        emailService.sendSimpleMessage(mailDto);
        log.info("메일 전송 완료");
        return "mail/sendResult";
    }
}
