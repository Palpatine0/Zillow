package com.example.service.impl;

import com.example.dao.FileDao;
import com.example.entity.Banner;
import com.example.entity.HouseImage;
import com.example.service.FileService;
import com.example.vo.ZillowResult;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileDao fileDao;

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    // the IP address info that needs to append in blank image routes
    @Value("${zillow.fdfsBasePath.nginx.prefix}")
    private String nginxPrefix;

    @Override
    public ZillowResult getBanner() {
        ZillowResult result = new ZillowResult();
        try {
            // S1: get data
            Query query = new Query();
            query.with(PageRequest.of(0, 4));
            List<Banner> files = fileDao.selectBanner(query);

            // S2: append url to data's front, since it hasn't.and put them to a list
            ArrayList<Object> imgRes = new ArrayList<>();
            for (Banner file : files) {
                imgRes.add(nginxPrefix + file.getUrl());
            }

            // S3: feedback
            result.setResults(imgRes);
            result.setStatus(200);

        } catch (Exception e) {
            // failure handling
            e.printStackTrace();
            result.setStatus(500);
            result.setMsg("Failed to get banner");
        }
        return result;
    }

    @Override
    public ZillowResult uploadImage(byte[] fileBytes, String fileName) throws IOException {
        if (fileBytes.length != 0) {
            try {
                //1.convert the byte array in to input stream
                InputStream inputStream = new ByteArrayInputStream(fileBytes);
                //2.capture the substring of the file
                String fileSuffix = fileName.substring(fileName.lastIndexOf(".") + 1);
                //3.upload
                StorePath storePath = fastFileStorageClient.uploadFile(inputStream, inputStream.available(), fileSuffix, null);
                String fullPath = storePath.getFullPath();
                // 4.capture the img route and return
                String imageUrl = nginxPrefix + fullPath;
                HouseImage houseImage = new HouseImage();
                houseImage.setUrl(fullPath);
                fileDao.saveHouseImage(houseImage);
                return ZillowResult.ok(imageUrl);
            } catch (IOException ioException) {
                ZillowResult error = ZillowResult.error();
                error.setMsg("File upload failed");
                return error;
            }
        } else {
            ZillowResult error = ZillowResult.error();
            error.setMsg("File upload failed");
            return error;
        }
    }

    @Override
    public ZillowResult uploadImageNoPrefix(byte[] fileBytes, String fileName) throws IOException {
        if (fileBytes.length != 0) {
            try {
                //1.convert the byte array in to input stream
                InputStream inputStream = new ByteArrayInputStream(fileBytes);
                //2.capture the substring of the file
                String fileSuffix = fileName.substring(fileName.lastIndexOf(".") + 1);
                //3.upload
                StorePath storePath = fastFileStorageClient.uploadFile(inputStream, inputStream.available(), fileSuffix, null);
                String fullPath = storePath.getFullPath();
                // 4.capture the img route and return
                String imageUrl = fullPath;
                HouseImage houseImage = new HouseImage();
                houseImage.setUrl(fullPath);
                fileDao.saveHouseImage(houseImage);
                ZillowResult ok = ZillowResult.ok(imageUrl);
                ok.setMsg("File uploaded success");
                return ok;
            } catch (IOException ioException) {
                ZillowResult error = ZillowResult.error();
                error.setMsg("File upload failed");
                return error;
            }
        } else {
            ZillowResult error = ZillowResult.error();
            error.setMsg("File upload failed");
            return error;
        }
    }

    @Override
    public ZillowResult delete(String filePath) {
        fastFileStorageClient.deleteFile(filePath);
        return ZillowResult.ok();
    }
}
