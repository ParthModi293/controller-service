package com.clapcle.controller.controller;

import com.clapcle.communication.dto.MailTemplateMastDto;
import com.clapcle.communication.dto.MailTemplateMastFilterRequest;
import com.clapcle.communication.service.MailTemplateMastService;
import com.clapcle.core.common.ResponseBean;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/template-mast")
@Log4j2
public class MailTemplateMastController {

    private final MailTemplateMastService templateMastService;

    public MailTemplateMastController(MailTemplateMastService templateMastService) {
        this.templateMastService = templateMastService;
    }

    @PostMapping
    public ResponseEntity<?> createTemplate(@RequestBody @Valid MailTemplateMastDto templateMastDto) {
        log.info("create template mast : {}", templateMastDto);
        ResponseBean<?> responseBean = templateMastService.createTemplate(templateMastDto);
        return new ResponseEntity<>(responseBean, responseBean.getRStatus());
    }

    @GetMapping
    public ResponseEntity<?> getAllTemplates(@RequestBody @Valid MailTemplateMastFilterRequest templateMastFilterRequest) {
        log.info("get all template mast on event : {}", templateMastFilterRequest);
        ResponseBean<?> responseBean = templateMastService.getAllTemplates(templateMastFilterRequest);
        return new ResponseEntity<>(responseBean, responseBean.getRStatus());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTemplateById(@PathVariable int id) {
        log.info("get template mast on id : {}", id);
        ResponseBean<?> responseBean = templateMastService.getTemplateById(id);
        return new ResponseEntity<>(responseBean, responseBean.getRStatus());
    }

    @DeleteMapping("/{id}")
    public void deleteTemplate(@PathVariable int id) {
        templateMastService.deleteTemplate(id);
    }
}
