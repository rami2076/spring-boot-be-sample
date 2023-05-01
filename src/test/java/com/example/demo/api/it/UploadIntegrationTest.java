package com.example.demo.api.it;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UploadIntegrationTest {


    @Nested
    @DisplayName("method: upload")
    class Upload {
        @Test
        @DisplayName("正常なリクエストで外部APIから呼び出された場合にHTTPステータスが200で返却されるメッセージが正しいこと")
        @SuppressWarnings("JUnitMalformedDeclaration")
        void test1(@Autowired TestRestTemplate restTemplate) throws Exception {

            // Setup
            var filePath = Paths.get(Objects.requireNonNull(
                    getClass().getResource("/file/upload/request.txt")).toURI());
            var requestFilePart = Files.readAllBytes(filePath);


            //multipart
            MultiValueMap<String, Object> formMap = new LinkedMultiValueMap<>();
            //file-part
            // Removed it because it is a reference when calling api from bff.
            MockMultipartFile filePart = new MockMultipartFile(
                    "file",
                    "file.txt",
                    MediaType.TEXT_PLAIN_VALUE,
                    requestFilePart);
            var fileResource = new ByteArrayResource(filePart.getBytes()) {
                @Override
                public String getFilename() {
                    return filePart.getOriginalFilename();
                }
            };
            formMap.add("file", fileResource);

            //json-part
            var jsonPath = Paths.get(Objects.requireNonNull(
                    getClass().getResource("/file/upload/request.json")).toURI());
            var requestJsonPart = Files.readString(jsonPath);
            HttpHeaders jsonHeaders = new HttpHeaders();
            jsonHeaders.setContentType(MediaType.APPLICATION_JSON);
            formMap.add("jsonValue", new HttpEntity<>(requestJsonPart, jsonHeaders));

            //body
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            var body = new HttpEntity<>(formMap, headers);

            // Exec
            var response = restTemplate.postForEntity("/word-list", body, String.class);

            // Verify
            Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
            Assertions.assertEquals("MyRequest(isUseDefaultWordList=true):file.txt", response.getBody());
        }
    }

}
