package com.example.demo.api.infrastructures;

import com.example.demo.api.presentations.MyRequest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Repository
public class FileApi {


    private final RestTemplate restTemplate;


    public FileApi(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public void upload(MyRequest myRequest, MultipartFile file){


    }
}
