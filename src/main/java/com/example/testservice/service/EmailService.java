package com.example.testservice.service;


import com.example.testservice.model.EmailBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.common.common.ResponseBean;
import org.communication.dto.EmailDto;
import org.communication.repository.TemplateDetailRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Log4j2
@Service
public class EmailService {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final TemplateDetailRepository templateDetailsRepository;
    private final RestTemplate restTemplate;


    public EmailService(KafkaTemplate<String, Object> kafkaTemplate, TemplateDetailRepository templateDetailsRepository, RestTemplate restTemplate) {

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
                RestTemplate rest = new RestTemplate();
                String path = "restEmail/send";
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);

                HttpEntity<EmailDto> requestEntity = new HttpEntity<>(emailDto, headers);
                ResponseEntity<ResponseBean> responseBean = rest.exchange(authUrl + path, HttpMethod.POST,
                        requestEntity, ResponseBean.class);
                return new ResponseBean<>(responseBean.getBody().getRStatus(), responseBean.getBody().getRMsg(), responseBean.getBody().getDisplayMessage(), null);

            } catch (HttpClientErrorException e) {
                log.error(e);
                return null;
            }

        } else {
            kafkaTemplate.send("email-topic", emailDto.getPriority(), null, new ObjectMapper().writeValueAsString(emailDto));
            return new ResponseBean<>(HttpStatus.OK, "Email sent successfully", null);
        }

    }


    public EmailDto emailDtoMaker(EmailBean emailBean) throws Exception {
        Map<String, Object> mailDetails = templateDetailsRepository.getMailDetails(emailBean.getTemplateId(), emailBean.getDbName());
        System.out.println("mailDetails Object------->>>: " + mailDetails);
        if (mailDetails == null || mailDetails.isEmpty()) {
            mailDetails = templateDetailsRepository.getMailDetails(emailBean.getTemplateId(), "public");
        }
        if (emailBean.getBcc().size() + emailBean.getTo().size() + emailBean.getCc().size() > Integer.parseInt(mailDetails.get("maxLimit").toString())){
            throw new Exception("Mail limit exceeded");
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

        Pattern pattern = Pattern.compile("##.*?##");
        Matcher bodyMatcher = pattern.matcher(body);
        Matcher subjectMatcher = pattern.matcher(subject);
        if (bodyMatcher.find()) {
            throw new Exception("Invalid body found");
        }
        if (subjectMatcher.find()) {
            throw new Exception("Invalid subject found");
        }



        EmailDto emailDto = new EmailDto();
        emailDto.setFrom(mailDetails.get("fromEmailId").toString());
        emailDto.setTo(emailBean.getTo());
        emailDto.setSubject(subject);
        emailDto.setBody(body);
        emailDto.setCc(emailBean.getCc());
        emailDto.setBcc(emailBean.getBcc());
        emailDto.setVersion(mailDetails.get("version").toString());
        emailDto.setAttachments(emailBean.getFile());
        emailDto.setHost(mailDetails.get("host").toString());
        emailDto.setPassword(mailDetails.get("password").toString());
        emailDto.setPort(Integer.parseInt(mailDetails.get("port").toString()));
        emailDto.setPriority(Integer.parseInt(mailDetails.get("priority").toString()));

        return emailDto;
    }


}
