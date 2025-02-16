package com.example.testservice.controller;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.common.common.ResponseBean;
import org.communication.dto.SmsTemplateMasterDto;
import org.communication.dto.SmsTemplateMasterFilterRequest;
import org.communication.entity.SmsTemplateMaster;
import org.communication.service.SmsTemplateMasterService;
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
        ResponseBean<Map<String, Object>> responseBean  =smsTemplateMasterService.addOrUpdateSmsTemplate(requestDTO);

         return new ResponseEntity<>(responseBean, responseBean.getRStatus());
    }


   /*@GetMapping(value = "/getAll")
    public ResponseEntity< ResponseBean<List<SmsTemplateMaster>>> getAllSmsTemplates(
            @RequestParam(required = false) String searchText,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
       log.info("get all Sms Template: ");
        SmsTemplateMasterFilterRequest filterRequest = new SmsTemplateMasterFilterRequest(searchText, page, size);
       ResponseBean<List<SmsTemplateMaster>> responseBean = smsTemplateMasterService.getAllSmsTemplates(filterRequest);
       return new ResponseEntity<>(responseBean, responseBean.getRStatus());
    }*/

    @PostMapping(value = "/getAll")
    public ResponseEntity<ResponseBean<List<SmsTemplateMaster>>> getAllSmsTemplates(
            @RequestBody @Valid SmsTemplateMasterFilterRequest filterRequest) {
        log.info("Fetching all SMS Templates with filter: {}", filterRequest);

        ResponseBean<List<SmsTemplateMaster>> responseBean = smsTemplateMasterService.getAllSmsTemplates(filterRequest);

        return new ResponseEntity<>(responseBean, responseBean.getRStatus());
    }


    @GetMapping("/{id}")
    public ResponseEntity< ResponseBean<SmsTemplateMaster>> getSmsTemplateById(@PathVariable Integer id) {
        log.info("fetch sms Template by id: {}  ", id);
        ResponseBean<SmsTemplateMaster> response = smsTemplateMasterService.findSmsTemplateById(id);
        return new ResponseEntity<>(response, response.getRStatus());
    }

}
