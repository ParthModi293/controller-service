package com.clapcle.controller.controller;

import com.clapcle.communication.dto.SmsTemplateMasterDto;
import com.clapcle.communication.dto.SmsTemplateMasterFilterRequest;
import com.clapcle.communication.entity.SmsTemplateMaster;
import com.clapcle.communication.service.SmsTemplateMasterService;
import com.clapcle.core.common.ResponseBean;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sms-template")
@CrossOrigin
@Log4j2
public class SmsTemplateMasterController {

    private final SmsTemplateMasterService smsTemplateMasterService;

    public SmsTemplateMasterController(SmsTemplateMasterService smsTemplateMasterService) {
        this.smsTemplateMasterService = smsTemplateMasterService;
    }

    @PostMapping("/create-update")
    public ResponseEntity<ResponseBean<Map<String, Object>>> saveSmsTemplate(@RequestBody @Valid SmsTemplateMasterDto requestDTO) {
        log.info("Save Sms template: {}  ", requestDTO);
        ResponseBean<Map<String, Object>> responseBean = smsTemplateMasterService.addOrUpdateSmsTemplate(requestDTO);

        return new ResponseEntity<>(responseBean, responseBean.getRStatus());
    }


    @GetMapping(value = "/getAll")
    public ResponseEntity<ResponseBean<List<SmsTemplateMaster>>> getAllSmsTemplates(
            @RequestParam(required = false) String searchText,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        log.info("get all Sms Template: ");
        SmsTemplateMasterFilterRequest filterRequest = new SmsTemplateMasterFilterRequest(searchText, page, size);
        ResponseBean<List<SmsTemplateMaster>> responseBean = smsTemplateMasterService.getAllSmsTemplates(filterRequest);
        return new ResponseEntity<>(responseBean, responseBean.getRStatus());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseBean<SmsTemplateMaster>> getSmsTemplateById(@PathVariable Integer id) {
        log.info("fetch sms Template by id: {}  ", id);
        ResponseBean<SmsTemplateMaster> response = smsTemplateMasterService.findSmsTemplateById(id);
        return new ResponseEntity<>(response, response.getRStatus());
    }

}
