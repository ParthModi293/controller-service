package com.clapcle.controller.controller;

import com.clapcle.controller.model.EmailBean;
import com.clapcle.controller.service.EmailService;
import com.clapcle.core.common.ResponseBean;
import lombok.extern.log4j.Log4j2;
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
