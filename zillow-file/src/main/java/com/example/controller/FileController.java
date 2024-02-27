package com.example.controller;


import com.example.service.FileService;
import com.example.vo.ZillowResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;


@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private FileService fileService;

    @GetMapping("/getBanner")
    public ZillowResult File() {
        return fileService.getBanner();
    }


    @PostMapping("/uploadImage")
    public ZillowResult upload(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        return fileService.uploadImage(bytes, file.getOriginalFilename());
    }

    @DeleteMapping("/delete")
    public ZillowResult delete(String filePath) {
        return fileService.delete(filePath);
    }

    @GetMapping("/test")
    public ZillowResult test() {
        String filePath = "/Users/sheevpalpatine/Desktop/Coding/Full_Stack_Projects/Zillow/Project/Zillow/Zillow/zillow-file/src/main/java/com/example/test/test.json";

        String jsonString = "";
        try {
            // Read the content of the file into a byte array
            byte[] jsonData = Files.readAllBytes(Paths.get(filePath));
            jsonString = new String(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ZillowResult zillowResult = new ZillowResult();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> dataMap = objectMapper.readValue(jsonString, Map.class);
            zillowResult.setData(dataMap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return zillowResult;
    }

}
