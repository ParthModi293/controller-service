package com.example.testservice.controller;

import jakarta.validation.Valid;
import org.common.common.ResponseBean;
import org.communication.dto.SmsTemplateMasterDto;
import org.communication.service.SmsTemplateMasterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sms-template")
public class SmsTemplateMasterController {

    private final SmsTemplateMasterService smsTemplateMasterService;

    public SmsTemplateMasterController(SmsTemplateMasterService smsTemplateMasterService) {
        this.smsTemplateMasterService = smsTemplateMasterService;
    }

    @PostMapping("/create-update")
    public ResponseEntity<ResponseBean<?>> saveSmsTemplate(@RequestBody @Valid SmsTemplateMasterDto requestDTO) {
        ResponseBean<?> responseBean  =smsTemplateMasterService.addOrUpdateSmsTemplate(requestDTO);
        return new ResponseEntity<>(responseBean,responseBean.getRStatus());
    }
}
