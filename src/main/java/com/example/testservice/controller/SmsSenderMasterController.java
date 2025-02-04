package com.example.testservice.controller;

import jakarta.validation.Valid;
import org.common.common.ResponseBean;
import org.communication.dto.SmsSenderMasterDto;
import org.communication.service.SmsSenderMasterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sms-sender")
public class SmsSenderMasterController {

    private final SmsSenderMasterService smsSenderMasterService;

    public SmsSenderMasterController(SmsSenderMasterService smsSenderMasterService) {
        this.smsSenderMasterService = smsSenderMasterService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseBean<?>> createSenderMaster(@RequestBody @Valid SmsSenderMasterDto requestDTO) {
        ResponseBean<?> responseBean  = smsSenderMasterService.createSenderMaster(requestDTO);
        return new ResponseEntity<>(responseBean,responseBean.getRStatus());
    }

}
