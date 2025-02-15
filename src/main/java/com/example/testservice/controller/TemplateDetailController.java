package com.example.testservice.controller;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.common.common.ResponseBean;
import org.communication.dto.TemplateDetailsDto;
import org.communication.service.TemplateDetailsService;
import org.jsoup.nodes.Document;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/template-detail")
@Log4j2
public class TemplateDetailController {

    private final TemplateDetailsService templateDetailsService;

    public TemplateDetailController(TemplateDetailsService templateDetailsService) {
        this.templateDetailsService = templateDetailsService;
    }

    @PostMapping
    public ResponseEntity<?> createTemplateDetail(@RequestBody @Valid TemplateDetailsDto templateDetailsDto) {
        log.info("create template detail : {}",templateDetailsDto);
        ResponseBean<?> responseBean = templateDetailsService.createTemplateDetail(templateDetailsDto);
        return new ResponseEntity<>(responseBean, responseBean.getRStatus());
    }

    @GetMapping("/{templateMastId}")
    public ResponseEntity<?> getTemplateDetail(@PathVariable("templateMastId") int templateMastId) {
        log.info("get template detail on template mast id: {}",templateMastId);
        ResponseBean<?> responseBean = templateDetailsService.getTemplateDetail(templateMastId);
        return new ResponseEntity<>(responseBean, responseBean.getRStatus());
    }

    @GetMapping("getAll/{templateMastId}")
    public ResponseEntity<?> getAllTemplateDetail(@PathVariable("templateMastId") int templateMastId) {
        log.info("get all template detail on template mast id: {}",templateMastId);
        ResponseBean<?> responseBean = templateDetailsService.getAllTemplateDetail(templateMastId);
        return new ResponseEntity<>(responseBean, responseBean.getRStatus());
    }

}
