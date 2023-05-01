package com.example.demo.api.presentations;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FirstController {


    @PostMapping(value = "/word-list")
    public String upload(@RequestPart("jsonValue") MyRequest request, @RequestPart("file") MultipartFile file) {
        return request + ":" + file.getOriginalFilename();
    }
}
