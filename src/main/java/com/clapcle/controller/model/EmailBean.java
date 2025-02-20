package com.clapcle.controller.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Data
public class EmailBean {

    private int templateId;
    private String dbName;
    private List<String> to;
    private List<String> cc;
    private List<String> bcc;
    private Map<String, Object> subjectPlaceHolder;
    private Map<String, Object> bodyPlaceHolder;
    private List<String> file;

}
