package com.example.testservice.controller;

import jakarta.validation.Valid;
import org.common.common.ResponseBean;
import org.communication.dto.TemplateDetailsDto;
import org.communication.service.TemplateDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/template-detail")
public class TemplateDetailController {

    private final TemplateDetailsService templateDetailsService;

    public TemplateDetailController(TemplateDetailsService templateDetailsService) {
        this.templateDetailsService = templateDetailsService;
    }

    @PostMapping
    public ResponseEntity<?> createTemplateDetail(@RequestBody @Valid TemplateDetailsDto templateDetailsDto) {
        ResponseBean<?> responseBean = templateDetailsService.createTemplateDetail(templateDetailsDto);
        return new ResponseEntity<>(responseBean, responseBean.getRStatus());
    }

    @GetMapping("/{templateMastId}")
    public ResponseEntity<?> getTemplateDetail(@PathVariable("templateMastId") int templateMastId) {
        ResponseBean<?> responseBean = templateDetailsService.getTemplateDetail(templateMastId);
        return new ResponseEntity<>(responseBean, responseBean.getRStatus());
    }

    @GetMapping("getAll/{templateMastId}")
    public ResponseEntity<?> getAllTemplateDetail(@PathVariable("templateMastId") int templateMastId) {
        ResponseBean<?> responseBean = templateDetailsService.getAllTemplateDetail(templateMastId);
        return new ResponseEntity<>(responseBean, responseBean.getRStatus());
    }

}
