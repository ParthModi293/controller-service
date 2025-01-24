package com.example.testservice.controller;

import jakarta.validation.Valid;
import org.common.common.ResponseBean;
import org.communication.dto.TemplateMastDto;
import org.communication.dto.TemplateMastFilterRequest;
import org.communication.service.TemplateMastService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/template-mast")
public class TemplateMastController {

    private final TemplateMastService templateMastService;

    public TemplateMastController(TemplateMastService templateMastService) {
        this.templateMastService = templateMastService;
    }

    @PostMapping
    public ResponseEntity<?> createTemplate(@RequestBody @Valid TemplateMastDto templateMastDto) {
        ResponseBean<?> responseBean = templateMastService.createTemplate(templateMastDto);
        return new ResponseEntity<>(responseBean, responseBean.getRStatus());
    }

    @GetMapping
    public ResponseEntity<?> getAllTemplates(@RequestBody @Valid TemplateMastFilterRequest templateMastFilterRequest) {
        ResponseBean<?> responseBean = templateMastService.getAllTemplates(templateMastFilterRequest);
        return new ResponseEntity<>(responseBean, responseBean.getRStatus());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTemplateById(@PathVariable int id) {
        ResponseBean<?> responseBean = templateMastService.getTemplateById(id);
        return new ResponseEntity<>(responseBean, responseBean.getRStatus());
    }

    @DeleteMapping("/{id}")
    public void deleteTemplate(@PathVariable int id) {
        templateMastService.deleteTemplate(id);
    }
}
