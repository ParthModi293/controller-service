package com.example.testservice.controller;

import jakarta.validation.Valid;
import org.common.common.Const;
import org.common.common.ResponseBean;
import org.communication.dto.SmsProviderMasterDto;
import org.communication.service.SmsProviderMasterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/sms-provider")
public class SmsProviderMasterController {

    private final SmsProviderMasterService smsProviderMasterService;

    public SmsProviderMasterController(SmsProviderMasterService smsProviderMasterService) {
        this.smsProviderMasterService = smsProviderMasterService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createSmsProvider(@RequestBody @Valid SmsProviderMasterDto requestDTO) {

        ResponseBean<?> responseBean = smsProviderMasterService.createSmsProvider(requestDTO);

        return new ResponseEntity<>(responseBean,responseBean.getRStatus());
    }
}
