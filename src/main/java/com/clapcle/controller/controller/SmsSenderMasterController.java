package com.clapcle.controller.controller;

import com.clapcle.communication.dto.SmsSenderMasterDto;
import com.clapcle.communication.service.SmsSenderMasterService;
import com.clapcle.core.common.ResponseBean;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/sms-sender")
@CrossOrigin
@Log4j2
public class SmsSenderMasterController {

    private final SmsSenderMasterService smsSenderMasterService;

    public SmsSenderMasterController(SmsSenderMasterService smsSenderMasterService) {
        this.smsSenderMasterService = smsSenderMasterService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseBean<Map<String, Object>>> createSenderMaster(@RequestBody @Valid SmsSenderMasterDto requestDTO) {
        log.info("Save Sms Sender Master: {}  ", requestDTO);
        ResponseBean<Map<String, Object>> responseBean = smsSenderMasterService.createSenderMaster(requestDTO);
        return new ResponseEntity<>(responseBean, responseBean.getRStatus());
    }

}
