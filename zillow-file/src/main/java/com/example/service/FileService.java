package com.example.service;

import com.example.vo.BaseResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    BaseResult getBanners();

    BaseResult uploadFile(MultipartFile file, String path);
}
