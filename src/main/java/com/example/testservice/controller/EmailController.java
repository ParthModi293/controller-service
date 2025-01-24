package com.example.testservice.controller;

import com.example.testservice.model.EmailBean;
import com.example.testservice.service.EmailService;
import org.common.common.ResponseBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email-service")
@CrossOrigin
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }


    @PostMapping("/send-email")
    public ResponseEntity<ResponseBean<?>> mailSender(@RequestBody EmailBean emailBean) throws Exception {
        ResponseBean<?> responseBean = emailService.mailSender(emailBean);
        return new ResponseEntity<>(responseBean, responseBean.getRStatus());
    }


}
