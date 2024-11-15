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

    @PostMapping("/uploadImage")
    public BaseResult uploadImage(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        return fileService.uploadImage(bytes, file.getOriginalFilename());
    }

    @PostMapping("/uploadImageNoPrefix")
    public BaseResult uploadImageNoPrefix(@RequestParam("file") MultipartFile file) throws IOException {
        try {
            byte[] bytes = file.getBytes();
            return fileService.uploadImageNoPrefix(bytes, file.getOriginalFilename());
        } catch (Exception e) {
            e.printStackTrace();
            BaseResult result = new BaseResult();
            result.setStatus(500);
            result.setMsg("Failed to upload file due to an error: " + e.getMessage());
            return result;
        }
    }


    @DeleteMapping("/delete")
    public BaseResult delete(String filePath) {
        return fileService.delete(filePath);
    }


}
