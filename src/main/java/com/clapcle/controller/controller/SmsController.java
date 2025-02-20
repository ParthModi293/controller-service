package com.clapcle.controller.controller;

import com.clapcle.controller.model.SMSBean;
import com.clapcle.controller.service.SmsService;
import com.clapcle.core.common.ResponseBean;
import lombok.extern.log4j.Log4j2;
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
