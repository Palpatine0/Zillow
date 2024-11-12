package com.example.service.impl;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.example.constant.AWSS3Constant;
import com.example.dao.FileDao;
import com.example.entity.Banner;
import com.example.entity.HouseImage;
import com.example.service.FileService;
import com.example.vo.BaseResult;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileDao fileDao;

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    private AmazonS3 s3Client;

    // the IP address info that needs to append in blank image routes
    @Value("${zillow.fdfsBasePath.nginx.prefix}")
    private String nginxPrefix;

    @Override
    public BaseResult getBanners() {
        BaseResult result = new BaseResult();
        try {
            // S1: get data
            Query query = new Query();
            query.with(PageRequest.of(0, 4));
            List<Banner> files = fileDao.findBanners(query);

            // S2: append url to data's front, since it hasn't.and put them to a list
            ArrayList<Object> imgRes = new ArrayList<>();
            for (Banner file : files) {
                imgRes.add(file.getUrl());
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

    @PostConstruct
    private void initialize() {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(AWSS3Constant.ACCESS_KEY, AWSS3Constant.SECRET_KEY);
        s3Client = AmazonS3ClientBuilder.standard()
            .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
            .withRegion(AWSS3Constant.REGION)
            .build();
    }


    @Override
    public BaseResult upload(MultipartFile multipartFile, String path) {
        String filePath = path + "/" + multipartFile.getOriginalFilename();
        try {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(multipartFile.getContentType());
            objectMetadata.setContentLength(multipartFile.getSize());
            s3Client.putObject(AWSS3Constant.BUCKET_NAME, filePath, multipartFile.getInputStream(), objectMetadata);
        } catch (IOException e) {
            BaseResult.error(String.valueOf(e));
        }
        return BaseResult.ok(filePath);
    }

    @Override
    public BaseResult uploadImage(byte[] fileBytes, String fileName) throws IOException {
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
                return BaseResult.ok(imageUrl);
            } catch (IOException ioException) {
                BaseResult error = BaseResult.error();
                error.setMsg("File upload failed");
                return error;
            }
        } else {
            BaseResult error = BaseResult.error();
            error.setMsg("File upload failed");
            return error;
        }
    }

    @Override
    public BaseResult uploadImageNoPrefix(byte[] fileBytes, String fileName) throws IOException {
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
                BaseResult ok = BaseResult.ok(imageUrl);
                ok.setMsg("File uploaded success");
                return ok;
            } catch (IOException ioException) {
                BaseResult error = BaseResult.error();
                error.setMsg("File upload failed");
                return error;
            }
        } else {
            BaseResult error = BaseResult.error();
            error.setMsg("File upload failed");
            return error;
        }
    }

    @Override
    public BaseResult delete(String filePath) {
        fastFileStorageClient.deleteFile(filePath);
        return BaseResult.ok();
    }
}
