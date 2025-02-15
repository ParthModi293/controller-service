package com.example.testservice.service;

import com.example.testservice.model.Recipients;
import com.example.testservice.model.SMSBean;
import org.common.common.ResponseBean;
import org.communication.dto.SmsDetailsDto;
import org.communication.dto.SmsDto;
import org.communication.repository.SmsMasterRepository;
import org.communication.service.SmsSendService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SmsService {

    private final SmsMasterRepository smsMasterRepository;
    private final SmsSendService smsSendService;

    public SmsService(SmsMasterRepository smsMasterRepository, SmsSendService smsSendService) {
        this.smsMasterRepository = smsMasterRepository;
        this.smsSendService = smsSendService;
    }

    /**
     * @param smsBean
     * @return ResponseBean
     * @author Zeel
     * @apiNote This api is used to send SMS either it is single or bulk
     */
    public ResponseBean sendSms(SMSBean smsBean) {
        SmsDetailsDto smsDetails = smsMasterRepository.getSmsDetails(smsBean.getSmsMasterId());
        SmsDto smsDto = new SmsDto();
        smsDto.setTemplate_id(smsDetails.getServiceProviderTemplateCode());
        smsDto.setShort_url("1");
        smsDto.setShort_url_expiry("100");
        smsDto.setRealTimeResponse("1");
        // TODO: R&D on the sender code of the SMS
//       smsDto.setSender(smsDetails.getSenderCode());
        List<Map<String, Object>> recipientsList = new ArrayList<>();
        for (Recipients recipients : smsBean.getRecipients()) {
            Map<String, Object> map = new HashMap<>();
            map.put("mobiles", recipients.getMobile());
            map.put("name", recipients.getPlaceHolders().get("name"));
            recipientsList.add(map);
        }
        smsDto.setRecipients(recipientsList);
        return smsSendService.sendSms(smsDetails, smsDto);
    }


}
