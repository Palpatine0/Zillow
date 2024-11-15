package com.example.service;

import com.example.vo.BaseResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    BaseResult getBanners();

    BaseResult uploadFile(MultipartFile file, String path);

    BaseResult uploadImage(byte[] fileBytes, String fileName) throws IOException;

    BaseResult uploadImageNoPrefix(byte[] fileBytes, String fileName) throws IOException;

    BaseResult delete(String filePath);
}
