package com.example.testservice.controller;

import jakarta.validation.Valid;
import org.common.common.ResponseBean;
import org.communication.dto.SmsTemplateMasterDto;
import org.communication.dto.SmsTemplateMasterFilterRequest;
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
    public ResponseEntity<?> saveSmsTemplate(@RequestBody @Valid SmsTemplateMasterDto requestDTO) {
        ResponseBean<?> responseBean  =smsTemplateMasterService.addOrUpdateSmsTemplate(requestDTO);

         return new ResponseEntity<>(responseBean, responseBean.getRStatus());
    }


   @GetMapping(value = "/getAll")
    public ResponseEntity<?> getAllSmsTemplates(
            @RequestParam(required = false) String searchText,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        SmsTemplateMasterFilterRequest filterRequest = new SmsTemplateMasterFilterRequest(searchText, page, size);
        ResponseBean<?> responseBean = smsTemplateMasterService.getAllSmsTemplates(filterRequest);
        return new ResponseEntity<>(responseBean, responseBean.getRStatus());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSmsTemplateById(@PathVariable Integer id) {
        ResponseBean<?> response = smsTemplateMasterService.findSmsTemplateById(id);
        return new ResponseEntity<>(response, response.getRStatus());
    }

}
