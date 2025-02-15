package com.example.testservice.controller;

import jakarta.validation.Valid;
import org.common.common.ResponseBean;
import org.communication.dto.SmsMasterDto;
import org.communication.service.SmsMasterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/sms-master")
public class SmsMasterController {

    private final SmsMasterService smsMasterService;

    public SmsMasterController(SmsMasterService smsMasterService) {
        this.smsMasterService = smsMasterService;
    }

    @PostMapping("/create-update")
    public ResponseEntity<?> saveOrUpdateSmsMaster(@Valid @RequestBody SmsMasterDto dto) {
        ResponseBean<?> responseBean = smsMasterService.createOrUpdateSmsMaster(dto);

        return new ResponseEntity<>(responseBean,responseBean.getRStatus());
    }

}
