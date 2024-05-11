package com.example.controller;


import com.example.service.FileService;
import com.example.vo.ZillowResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private FileService fileService;

    @GetMapping("/getBanner")
    public ZillowResult file() {
        return fileService.getBanner();
    }


    @PostMapping("/uploadImage")
    public ZillowResult uploadImage(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        return fileService.uploadImage(bytes, file.getOriginalFilename());
    }

    @PostMapping("/uploadImageNoPrefix")
    public ZillowResult uploadImageNoPrefix(@RequestParam("file") MultipartFile file) throws IOException {
        try {
            byte[] bytes = file.getBytes();
            System.out.println("File uploaded successfully: " + file.getOriginalFilename());
            return fileService.uploadImageNoPrefix(bytes, file.getOriginalFilename());
        } catch (Exception e) {
            System.err.println("Error processing file: " + file.getOriginalFilename());
            e.printStackTrace();
            ZillowResult result = new ZillowResult();
            result.setStatus(500);
            result.setMsg("Failed to upload file due to an error: " + e.getMessage());
            return result;
        }
    }


    @DeleteMapping("/delete")
    public ZillowResult delete(String filePath) {
        return fileService.delete(filePath);
    }


}
