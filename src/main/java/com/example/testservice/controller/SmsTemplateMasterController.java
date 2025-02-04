package com.example.testservice.controller;

import jakarta.validation.Valid;
import org.common.common.ResponseBean;
import org.communication.dto.SmsTemplateMasterDto;
import org.communication.dto.SmsTemplateMasterFilterRequest;
import org.communication.dto.TemplateMastFilterRequest;
import org.communication.service.SmsTemplateMasterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public ResponseEntity<?> getAllSmsTemplates(@RequestBody @Valid SmsTemplateMasterFilterRequest templateMastFilterRequest) {
        ResponseBean<?> responseBean = smsTemplateMasterService.getAllSmsTemplates(templateMastFilterRequest);
        return new ResponseEntity<>(responseBean, responseBean.getRStatus());
    }
}
