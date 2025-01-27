package com.example.testservice.service;


import com.example.testservice.model.EmailBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.common.common.ResponseBean;
import org.communication.dto.EmailDto;
import org.communication.repository.TemplateDetailsRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Log4j2
@Service
public class EmailService {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final TemplateDetailsRepository templateDetailsRepository;
    private final RestTemplate restTemplate;


    public EmailService(KafkaTemplate<String, Object> kafkaTemplate, TemplateDetailsRepository templateDetailsRepository, RestTemplate restTemplate) {

        this.kafkaTemplate = kafkaTemplate;
        this.templateDetailsRepository = templateDetailsRepository;

        this.restTemplate = restTemplate;
    }

    @Value("${spring.auth.url}")
    private String authUrl;


    public ResponseBean<Void> mailSender(EmailBean emailBean) throws Exception {
        EmailDto emailDto = emailDtoMaker(emailBean);
        if (emailDto.getPriority() == 1) {
            try {
                String path = "restEmail/send";
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<EmailDto> requestEntity = new HttpEntity<>(emailDto, headers);
                ResponseEntity<ResponseBean> responseBean = restTemplate.exchange(authUrl + path, HttpMethod.POST,
                        requestEntity, ResponseBean.class);
                return new ResponseBean<>(responseBean.getBody().getRStatus(), responseBean.getBody().getRMsg(), responseBean.getBody().getDisplayMessage(), null);

            } catch (HttpClientErrorException e) {
                log.error(e);
                return null;
            }

        } else {
            kafkaTemplate.send("email-topic", emailDto.getPriority(), null, new ObjectMapper().writeValueAsString(emailDto));
            return null;
        }

    }


    public EmailDto emailDtoMaker(EmailBean emailBean) {
        Map<String, Object> mailDetails = templateDetailsRepository.getMailDetails(emailBean.getTemplateName(), emailBean.getDbName());
        if (mailDetails == null || mailDetails.isEmpty()) {
            mailDetails = templateDetailsRepository.getMailDetails(emailBean.getTemplateName(), "email");
        }


        String body = mailDetails.get("body").toString();
        String subject = mailDetails.get("subject").toString();

        for (Map.Entry<String, Object> entry : emailBean.getBodyPlaceHolder().entrySet()) {
            String placeholder = "##" + entry.getKey() + "##";
            body = body.replace(placeholder, entry.getValue().toString());

        }

        for (Map.Entry<String, Object> entry : emailBean.getSubjectPlaceHolder().entrySet()) {
            String placeholder = "##" + entry.getKey() + "##";

            subject = subject.replace(placeholder, entry.getValue().toString());
        }

        return EmailDto.builder()
                .from(mailDetails.get("fromEmailId").toString())
                .to(emailBean.getTo())
                .cc(emailBean.getCc())
                .bcc(emailBean.getBcc())
                .subject(subject)
                .body(body)
                .version(mailDetails.get("version").toString())
                .attachments(emailBean.getFile())
                .host(mailDetails.get("host").toString())
                .password(mailDetails.get("password").toString())
                .port(Integer.parseInt(mailDetails.get("port").toString()))
                .priority(Integer.parseInt(mailDetails.get("priority").toString()))
                .build();
    }


}
