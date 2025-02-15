package com.example.testservice.controller;

import com.example.testservice.model.SMSBean;
import com.example.testservice.service.SmsService;
import lombok.extern.log4j.Log4j2;
import org.common.common.ResponseBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sms")
@CrossOrigin
@Log4j2
public class SmsController {

    private final SmsService smsService;

    public SmsController(SmsService smsService) {
        this.smsService = smsService;
    }

    @PostMapping()
    public ResponseEntity<ResponseBean> sendSms(@RequestBody SMSBean smsBean) {
        log.info("Send SMS: {}", smsBean);
        ResponseBean responseBean = smsService.sendSms(smsBean);
        return new ResponseEntity<>(responseBean, responseBean.getRStatus());
    }
}
