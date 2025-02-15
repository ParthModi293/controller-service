package com.example.testservice.controller;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.common.common.ResponseBean;
import org.communication.dto.TemplateMastDto;
import org.communication.dto.TemplateMastFilterRequest;
import org.communication.service.TemplateMastService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/template-mast")
@Log4j2
public class TemplateMastController {

    private final TemplateMastService templateMastService;

    public TemplateMastController(TemplateMastService templateMastService) {
        this.templateMastService = templateMastService;
    }

    @PostMapping
    public ResponseEntity<?> createTemplate(@RequestBody @Valid TemplateMastDto templateMastDto) {
        log.info("create template mast : {}",templateMastDto);
        ResponseBean<?> responseBean = templateMastService.createTemplate(templateMastDto);
        return new ResponseEntity<>(responseBean, responseBean.getRStatus());
    }

    @GetMapping
    public ResponseEntity<?> getAllTemplates(@RequestBody @Valid TemplateMastFilterRequest templateMastFilterRequest) {
        log.info("get all template mast on event : {}",templateMastFilterRequest);
        ResponseBean<?> responseBean = templateMastService.getAllTemplates(templateMastFilterRequest);
        return new ResponseEntity<>(responseBean, responseBean.getRStatus());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTemplateById(@PathVariable int id) {
        log.info("get template mast on id : {}",id);
        ResponseBean<?> responseBean = templateMastService.getTemplateById(id);
        return new ResponseEntity<>(responseBean, responseBean.getRStatus());
    }

    @DeleteMapping("/{id}")
    public void deleteTemplate(@PathVariable int id) {
        templateMastService.deleteTemplate(id);
    }
}
