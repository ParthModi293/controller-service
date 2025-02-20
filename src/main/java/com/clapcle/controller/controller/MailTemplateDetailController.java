package com.clapcle.controller.controller;

import com.clapcle.communication.dto.MailTemplateDetailsDto;
import com.clapcle.communication.service.MailTemplateDetailsService;
import com.clapcle.core.common.ResponseBean;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/template-detail")
@Log4j2
public class MailTemplateDetailController {

    private final MailTemplateDetailsService templateDetailsService;

    public MailTemplateDetailController(MailTemplateDetailsService templateDetailsService) {
        this.templateDetailsService = templateDetailsService;
    }

    @PostMapping
    public ResponseEntity<?> createTemplateDetail(@RequestBody @Valid MailTemplateDetailsDto templateDetailsDto) {
        log.info("create template detail : {}", templateDetailsDto);
        ResponseBean<?> responseBean = templateDetailsService.createTemplateDetail(templateDetailsDto);
        return new ResponseEntity<>(responseBean, responseBean.getRStatus());
    }

    @GetMapping("/{templateMastId}")
    public ResponseEntity<?> getTemplateDetail(@PathVariable("templateMastId") int templateMastId) {
        log.info("get template detail on template mast id: {}", templateMastId);
        ResponseBean<?> responseBean = templateDetailsService.getTemplateDetail(templateMastId);
        return new ResponseEntity<>(responseBean, responseBean.getRStatus());
    }

    @GetMapping("getAll/{templateMastId}")
    public ResponseEntity<?> getAllTemplateDetail(@PathVariable("templateMastId") int templateMastId) {
        log.info("get all template detail on template mast id: {}", templateMastId);
        ResponseBean<?> responseBean = templateDetailsService.getAllTemplateDetail(templateMastId);
        return new ResponseEntity<>(responseBean, responseBean.getRStatus());
    }

}
