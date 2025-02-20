package com.clapcle.controller.controller;

import com.clapcle.communication.dto.SmsProviderMasterDto;
import com.clapcle.communication.service.SmsProviderMasterService;
import com.clapcle.core.common.ResponseBean;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/sms-provider")
@CrossOrigin
@Log4j2
public class SmsProviderMasterController {

    private final SmsProviderMasterService smsProviderMasterService;

    public SmsProviderMasterController(SmsProviderMasterService smsProviderMasterService) {
        this.smsProviderMasterService = smsProviderMasterService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseBean<Map<String, Object>>> createSmsProvider(@RequestBody @Valid SmsProviderMasterDto requestDTO) {
        log.info("Save Sms Provider Master: {}  ", requestDTO);
        ResponseBean<Map<String, Object>> responseBean = smsProviderMasterService.createSmsProvider(requestDTO);

        return new ResponseEntity<>(responseBean, responseBean.getRStatus());
    }
}
