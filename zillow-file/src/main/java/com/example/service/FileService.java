package com.example.service;

import com.example.vo.ZillowResult;

import java.io.IOException;

public interface FileService {
    ZillowResult getBanners();

    ZillowResult uploadImage(byte[] fileBytes, String fileName) throws IOException;

    ZillowResult uploadImageNoPrefix(byte[] fileBytes, String fileName) throws IOException;

    ZillowResult delete(String filePath);
}
