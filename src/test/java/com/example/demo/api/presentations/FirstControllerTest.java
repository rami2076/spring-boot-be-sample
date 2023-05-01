package com.example.demo.api.presentations;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.ResponseEntity.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;

@SpringBootTest
@AutoConfigureMockMvc
class FirstControllerTest {

    @Nested
    @DisplayName("method: upload")
    class Upload {
        @Test
        @DisplayName("正常なリクエストがの場合、HTTPステータスが200で返却され、正常終了すること")
        @SuppressWarnings("JUnitMalformedDeclaration")
        void test1(@Autowired MockMvc mvc) throws Exception {

            //Setup
            var filePath = Paths.get(Objects.requireNonNull(
                    getClass().getResource("/file/upload/request.txt")).toURI());
            var jsonPath = Paths.get(Objects.requireNonNull(
                    getClass().getResource("/file/upload/request.json")).toURI());
            var requestFilePart = Files.readAllBytes(filePath);
            var requestJsonPart = Files.readAllBytes(jsonPath);

            MockMultipartFile filePart = new MockMultipartFile(
                    "file",
                    "file.txt",
                    MediaType.TEXT_PLAIN_VALUE,
                    requestFilePart);

            MockMultipartFile jsonPart = new MockMultipartFile(
                    "jsonValue",
                    "request.json",
                    MediaType.APPLICATION_JSON_VALUE,
                    requestJsonPart);

            // Exec & Verify
            mvc.perform(multipart("/word-list")
                            .file(filePart)
                            .file(jsonPart)
                            .content(MediaType.MULTIPART_FORM_DATA_VALUE))
                    .andExpect(MockMvcResultMatchers.status().isOk());

        }
    }

}