package com.example.testservice.controller;

import com.example.testservice.model.EmailBean;
import com.example.testservice.service.EmailService;
import lombok.extern.log4j.Log4j2;
import org.common.common.ResponseBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email-service")
@CrossOrigin
@Log4j2
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }


    @PostMapping("/send-email")
    public ResponseEntity<ResponseBean<Void>> mailSender(@RequestBody EmailBean emailBean) throws Exception {
        log.info("Email Sender : {}  ", emailBean);
        ResponseBean<Void> responseBean = emailService.mailSender(emailBean);
        return new ResponseEntity<>(responseBean, responseBean.getRStatus());
    }



}
