package com.example.testservice.controller;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.common.common.ResponseBean;
import org.communication.dto.SmsMasterDto;
import org.communication.service.SmsMasterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/sms-master")
@CrossOrigin
@Log4j2
public class SmsMasterController {

    private final SmsMasterService smsMasterService;

    public SmsMasterController(SmsMasterService smsMasterService) {
        this.smsMasterService = smsMasterService;
    }

    @PostMapping("/create-update")
    public ResponseEntity<ResponseBean<Map<String,Object>>> saveOrUpdateSmsMaster(@Valid @RequestBody SmsMasterDto dto) {
        log.info("Save/Update Sms Master: {}  ", dto);
        ResponseBean<Map<String, Object>> responseBean = smsMasterService.createOrUpdateSmsMaster(dto);

        return new ResponseEntity<>(responseBean,responseBean.getRStatus());
    }

}
