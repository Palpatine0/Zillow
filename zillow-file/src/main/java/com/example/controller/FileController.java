package com.example.controller;


import com.example.service.FileService;
import com.example.vo.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private FileService fileService;

    @GetMapping("/getBanners")
    public BaseResult getBanners() {
        return fileService.getBanners();
    }

    @PostMapping("/uploadFile")
    public BaseResult uploadFile(MultipartFile file, String path) {
        return fileService.uploadFile(file, path);
    }
}
